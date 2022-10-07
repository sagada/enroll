package com.sugang.toys.command.professor.application;

import com.sugang.toys.command.common.exception.ErrorCode;
import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.CourseSchedule;
import com.sugang.toys.command.professor.domain.ProfessorOpenCourseScheduleValidator;
import com.sugang.toys.command.professor.infra.ProfessorOpenCourseScheduleValidateService;
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
public class ProfessorOpenCourseService {

    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;
    private final DepartmentRepository departmentRepository;
    private final ProfessorOpenCourseScheduleValidator professorOpenCourseScheduleValidator;

    @Autowired
    public ProfessorOpenCourseService(
            CourseRepository courseRepository
            , ProfessorRepository professorRepository
            , DepartmentRepository departmentRepository
            , ProfessorOpenCourseScheduleValidateService openCourseScheduleValidateService)
    {
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
        this.departmentRepository = departmentRepository;
        this.professorOpenCourseScheduleValidator = openCourseScheduleValidateService;
    }

    @Transactional
    public Long professorOpenCourse(ProfessorOpenCourseRequest professorOpenCourseRequest)
    {
        Professor professor = professorRepository.findById(professorOpenCourseRequest.getProfessorId())
                .orElseThrow(() -> new ProfessorException(ErrorCode.NONE_PROFESSOR));

        Department department = departmentRepository.findById(professorOpenCourseRequest.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("NOT EXISTS Department!"));

        Set<CourseSchedule> schedules = professorOpenCourseRequest.getCourseScheduleSet().stream()
                .map(ProfessorCourseScheduleDto::convert)
                .collect(Collectors.toSet());

        Course course = professor.openCourse(
                schedules
                , professorOpenCourseRequest.getCourseName()
                , department
                , professorOpenCourseRequest.getMaxCourseStudentCount()
                , professorOpenCourseScheduleValidator
        );

        return courseRepository.save(course).getId();
    }
}
