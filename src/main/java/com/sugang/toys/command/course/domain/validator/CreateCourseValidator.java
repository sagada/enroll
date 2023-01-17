package com.sugang.toys.command.course.domain.validator;

import com.sugang.toys.command.course.domain.Course;

public interface CreateCourseValidator {
    void validate(Course course);
}
