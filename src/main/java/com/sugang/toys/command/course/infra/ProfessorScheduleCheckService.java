package com.sugang.toys.command.course.infra;

import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.CourseSchedule;
import com.sugang.toys.command.course.domain.service.CourseScheduleOverlapCheckService;
import com.sugang.toys.command.professor.domain.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfessorScheduleCheckService {

    private final CourseRepository courseRepository;
    private final CourseScheduleOverlapCheckService courseScheduleOverlapCheckService;

    @Autowired
    public ProfessorScheduleCheckService(
            CourseRepository courseRepository
            , CourseScheduleOverlapCheckService courseScheduleOverlapCheckService)
    {
        this.courseRepository = courseRepository;
        this.courseScheduleOverlapCheckService = courseScheduleOverlapCheckService;
    }

    public void professorScheduleCheck(Professor professor, Set<CourseSchedule> openCourseScheduleList)
    {
        List<CourseSchedule> courseScheduleList = courseRepository.findByProfessorId(professor.getId()).stream()
                .flatMap(professorCourse -> professorCourse.getCourseSchedules().getCourseScheduleSet().stream())
                .collect(Collectors.toList());

        checkOverlapOpenScheduleSet(openCourseScheduleList, courseScheduleList);
    }

    private void checkOverlapOpenScheduleSet(Set<CourseSchedule> openCourseScheduleList, List<CourseSchedule> courseScheduleList) {
        for (CourseSchedule courseSchedule : courseScheduleList)
        {
            for (CourseSchedule openSchedule : openCourseScheduleList)
            {
                if (courseScheduleOverlapCheckService.isOverlap(courseSchedule, openSchedule))
                {
                    throw new RuntimeException("중복되는 시간표가 있습니다!");
                }
            }
        }
    }
}
