package com.sugang.toys.command.course.infra;

import com.sugang.toys.command.course.application.CreateCourseValidator;
import org.springframework.stereotype.Service;

@Service
public class CreateCourseValidatorImpl implements CreateCourseValidator {

    @Override
    public void checkDuplicateCourseName(String courseName) {

    }
}
