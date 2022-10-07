package com.sugang.toys.command.course.application;

import com.sugang.toys.command.common.exception.ErrorCode;
import com.sugang.toys.command.course.application.dto.CourseCreateCommand;
import com.sugang.toys.command.course.application.dto.CourseScheduleRequest;
import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.CourseSchedule;
import com.sugang.toys.command.course.domain.CreateCourseValidator;
import com.sugang.toys.command.course.domain.exception.CourseException;
import com.sugang.toys.command.course.infra.CreateCourseValidatorImpl;
import com.sugang.toys.command.department.domain.Department;
import com.sugang.toys.command.department.domain.DepartmentRepository;
import com.sugang.toys.command.professor.domain.Professor;
import com.sugang.toys.command.professor.domain.ProfessorRepository;
import com.sugang.toys.command.professor.domain.exception.ProfessorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OpenCourseService {

    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;
    private final com.sugang.toys.command.course.application.CreateCourseValidator openCourseValidator;
    private final ProfessorRepository professorRepository;
    private final CreateCourseValidator createCourseValidator;

    @Autowired
    public OpenCourseService(
            CourseRepository courseRepository
            , DepartmentRepository departmentRepository
            , CreateCourseValidatorImpl createCourseValidatorImpl
            , ProfessorRepository professorRepository
            , CreateCourseValidator createCourseValidator)
    {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
        this.openCourseValidator = createCourseValidatorImpl;
        this.professorRepository = professorRepository;
        this.createCourseValidator = createCourseValidator;
    }

    @Transactional
    public void openCourse(Long courseId)
    {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseException(ErrorCode.NONE_COURSE));

        course.createCourse();
    }

    @Transactional
    public Long createCourse(CourseCreateCommand courseCreateCommand)
    {
        Department department = departmentRepository.findById(courseCreateCommand.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("NOT EXISTS Department!"));

        Professor professor = professorRepository.findById(courseCreateCommand.getProfessorId())
                .orElseThrow(() -> new ProfessorException(ErrorCode.NONE_PROFESSOR));

        Set<CourseSchedule> schedules = courseCreateCommand.getCourseScheduleSet().stream()
                .map(CourseScheduleRequest::convert)
                .collect(Collectors.toSet());

        Course course = Course.createCourse(
                schedules
                , professor
                , courseCreateCommand.getCourseName()
                , department
                , courseCreateCommand.getMaxCourseStudentCount()
                , createCourseValidator
        );

        return courseRepository.save(course).getId();
    }
}
