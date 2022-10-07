package com.sugang.toys.command.course.infra;

import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.CourseSchedule;
import com.sugang.toys.command.course.domain.OpenCourseScheduleValidator;
import com.sugang.toys.command.professor.domain.Professor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OpenCourseScheduleValidateService implements OpenCourseScheduleValidator {

    private final CourseRepository courseRepository;

    public void openCourseScheduleCheck(Professor professor, Set<CourseSchedule> openCourseScheduleList)
    {
        List<CourseSchedule> courseScheduleList = courseRepository.findByProfessorId(professor.getId()).stream()
                .flatMap(professorCourse -> professorCourse.getCourseSchedules().getCourseScheduleList().stream())
                .collect(Collectors.toList());

        for (CourseSchedule courseSchedule : courseScheduleList)
        {
            if (courseSchedule.contain(openCourseScheduleList))
            {
                throw new RuntimeException("중복되는 시간표가 있습니다!");
            }
        }
    }
}
