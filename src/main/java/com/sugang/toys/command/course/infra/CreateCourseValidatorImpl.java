package com.sugang.toys.command.course.infra;

import com.sugang.toys.command.common.exception.ErrorCode;
import com.sugang.toys.command.course.domain.CourseName;
import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.CourseSchedule;
import com.sugang.toys.command.course.domain.CreateCourseValidator;
import com.sugang.toys.command.course.domain.exception.CourseException;
import com.sugang.toys.command.department.domain.Department;
import com.sugang.toys.command.professor.domain.Professor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public class CreateCourseValidatorImpl implements CreateCourseValidator {

    private final CourseRepository courseRepository;
    private final ProfessorScheduleCheckService professorScheduleCheckService;

    @Autowired
    public CreateCourseValidatorImpl(
            CourseRepository courseRepository
            , ProfessorScheduleCheckService professorScheduleCheckService)
    {
        this.courseRepository = courseRepository;
        this.professorScheduleCheckService = professorScheduleCheckService;
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
            return ;
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
        professorScheduleCheckService.professorScheduleCheck(professor, openCourseScheduleSet);
    }

    private void duplicateCourseName(String courseName)
    {
        if (courseRepository.existsByName(new CourseName(courseName)))
        {
            throw new CourseException(ErrorCode.DUPLICATE_COURSE_NAME);
        }
    }
}
