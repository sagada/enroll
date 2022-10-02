package com.sugang.toys.command.course.application;

import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.CourseSchedule;
import com.sugang.toys.command.department.domain.Department;
import com.sugang.toys.command.department.domain.DepartmentRepository;
import com.sugang.toys.command.professor.domain.Professor;
import com.sugang.toys.command.professor.domain.ProfessorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseAddService {

    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;
    private final DepartmentRepository departmentRepository;

    @Transactional
    public void addCourse(AddCourseRequest addCourseRequest)
    {
        Professor professor = professorRepository.findById(addCourseRequest.getProfessorId())
                .orElseThrow(() -> new RuntimeException("없는 교수"));

        Department department = departmentRepository.findById(addCourseRequest.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("없는 학과"));

        Set<CourseSchedule> schedules = addCourseRequest.getCourseScheduleSet().stream()
                .map(AddCourseRequest.CourseScheduleDto::convert)
                .collect(Collectors.toSet());

        Course course = Course.create(
                null
                , schedules
                , null
                , professor
                , addCourseRequest.getCourseName()
                , department
                , addCourseRequest.getMaxCourseStudentCount()
        );

        courseRepository.save(course);
    }
}
