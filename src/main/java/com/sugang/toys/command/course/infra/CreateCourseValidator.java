package com.sugang.toys.command.course.infra;

import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.CourseSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class CreateCourseValidator {

    private final CourseRepository courseRepository;

    @Autowired
    public CreateCourseValidator(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    private void validate(
            Course course
            , Set<CourseSchedule> openCourseScheduleSet
            , Set<CourseSchedule> professorCourseScheduleSet)
    {

    }
}
