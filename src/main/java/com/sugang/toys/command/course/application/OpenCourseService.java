package com.sugang.toys.command.course.application;

import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.CourseSchedule;
import com.sugang.toys.command.department.domain.Department;
import com.sugang.toys.command.department.domain.DepartmentRepository;
import com.sugang.toys.command.professor.domain.Professor;
import com.sugang.toys.command.professor.domain.ProfessorOpenCourseValidateService;
import com.sugang.toys.command.professor.domain.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OpenCourseService {

    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;
    private final DepartmentRepository departmentRepository;
    private final ProfessorOpenCourseValidateService professorOpenCourseValidateService;

    @Transactional
    public Long openCourse(OpenCourseRequest openCourseRequest)
    {
        Professor professor = professorRepository.findById(openCourseRequest.getProfessorId())
                .orElseThrow(() -> new RuntimeException("없는 교수"));

        Department department = departmentRepository.findById(openCourseRequest.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("없는 학과"));

        Set<CourseSchedule> schedules = openCourseRequest.getCourseScheduleSet().stream()
                .map(OpenCourseRequest.CourseScheduleDto::convert)
                .collect(Collectors.toSet());

        Course course = professor.openCourse(
                schedules
                , openCourseRequest.getCourseName()
                , department
                , openCourseRequest.getMaxCourseStudentCount()
                , professorOpenCourseValidateService
        );

        return courseRepository.save(course).getId();
    }
}
