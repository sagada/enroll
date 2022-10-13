package com.sugang.toys.command.course.application;

import com.sugang.toys.command.common.exception.ErrorCode;
import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.exception.CourseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenCourseService {

    private final CourseRepository courseRepository;

    @Autowired
    public OpenCourseService(CourseRepository courseRepository)
    {
        this.courseRepository = courseRepository;
    }

    public void openCourse(Long courseId)
    {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseException(ErrorCode.NONE_COURSE));

        course.open();
    }
}
