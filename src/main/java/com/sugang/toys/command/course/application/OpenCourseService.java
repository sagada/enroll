package com.sugang.toys.command.course.application;

import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.CourseSchedule;
import com.sugang.toys.command.department.domain.Department;
import com.sugang.toys.command.department.domain.DepartmentRepository;
import com.sugang.toys.command.professor.domain.Professor;
import com.sugang.toys.command.professor.domain.OpenCourseScheduleValidateService;
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
    private final OpenCourseScheduleValidateService openCourseScheduleValidateService;

    @Transactional
    public Long openCourse(OpenCourseRequest openCourseRequest)
    {
        Professor professor = professorRepository.findById(openCourseRequest.getProfessorId())
                .orElseThrow(() -> new RuntimeException("NOT EXISTS professor!"));

        Department department = departmentRepository.findById(openCourseRequest.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("NOT EXISTS Department!"));

        Set<CourseSchedule> schedules = openCourseRequest.getCourseScheduleSet().stream()
                .map(OpenCourseRequest.CourseScheduleDto::convert)
                .collect(Collectors.toSet());

        Course course = professor.openCourse(
                schedules
                , openCourseRequest.getCourseName()
                , department
                , openCourseRequest.getMaxCourseStudentCount()
                , openCourseScheduleValidateService
        );

        return courseRepository.save(course).getId();
    }
}
