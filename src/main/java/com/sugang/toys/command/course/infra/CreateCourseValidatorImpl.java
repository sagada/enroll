package com.sugang.toys.command.course.infra;

import com.sugang.toys.command.common.exception.ErrorCode;
import com.sugang.toys.command.course.domain.*;
import com.sugang.toys.command.course.domain.CreateCourseValidator;
import com.sugang.toys.command.course.domain.exception.CourseException;
import com.sugang.toys.command.course.domain.service.CourseScheduleOverlapCheckService;
import com.sugang.toys.command.department.domain.Department;
import com.sugang.toys.command.professor.domain.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CreateCourseValidatorImpl implements CreateCourseValidator {

    private final CourseRepository courseRepository;
    private final CourseScheduleOverlapCheckService courseScheduleOverlapCheckService;

    @Autowired
    public CreateCourseValidatorImpl(CourseRepository courseRepository)
    {
        this.courseRepository = courseRepository;
        this.courseScheduleOverlapCheckService = new CourseScheduleOverlapCheckService();
    }

    public void validate(
            Professor professor
            , Department department
            , String courseName
            , Set<CourseSchedule> openCourseScheduleSet
    )
    {
        if (professor == null)
        {
            throw new RuntimeException("professor is null");
        }

        if (department == null)
        {
            throw new RuntimeException("department is null");
        }

        if (openCourseScheduleSet.isEmpty())
        {
            throw new RuntimeException("스케줄이 비었습니다.");
        }

        duplicateCourseName(courseName);
        professorScheduleCheck(professor, openCourseScheduleSet);
    }

    private void professorScheduleCheck(Professor professor, Set<CourseSchedule> openCourseScheduleList)
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

    private void duplicateCourseName(String courseName)
    {
        if (courseRepository.existsByName(new CourseName(courseName)))
        {
            throw new CourseException(ErrorCode.DUPLICATE_COURSE_NAME);
        }
    }
}
