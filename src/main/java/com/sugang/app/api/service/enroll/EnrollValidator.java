package com.sugang.app.api.service.enroll;

import com.sugang.app.domain.course.Course;
import com.sugang.app.domain.student.Student;

public interface EnrollValidator {
    void validate(Course course, Student student);
}
