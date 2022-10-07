package com.sugang.toys.command.course.application;

import com.sugang.toys.command.common.exception.ErrorCode;
import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.CourseSchedule;
import com.sugang.toys.command.course.domain.exception.CourseException;
import com.sugang.toys.command.department.domain.Department;
import com.sugang.toys.command.department.domain.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class OpenCourseService {

    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;
    private final CreateCourseValidator openCourseValidator;

    @Autowired
    public OpenCourseService(
            CourseRepository courseRepository
            , DepartmentRepository departmentRepository
            , CreateCourseValidatorImpl createCourseValidatorImpl)
    {
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
        this.openCourseValidator = createCourseValidatorImpl;
    }

    @Transactional
    public void openCourse(Long courseId)
    {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseException(ErrorCode.NONE_COURSE));

        course.open();
    }

    @Transactional
    public Long createCourse(CourseCreateRequest courseCreateRequest)
    {
        Department department = departmentRepository.findById(courseCreateRequest.getDepartmentId())
                .orElseThrow(() -> new RuntimeException("NOT EXISTS Department!"));

        Set<CourseSchedule> schedules = courseCreateRequest.getCourseScheduleSet().stream()
                .map(CourseScheduleDto::convert)
                .collect(Collectors.toSet());

        Course course = Course.open(
                schedules
                , null
                , courseCreateRequest.getCourseName()
                , department
                , courseCreateRequest.getMaxCourseStudentCount()
        );

        return courseRepository.save(course).getId();
    }
}
