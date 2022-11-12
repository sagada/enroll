package com.sugang.toys.command.enroll.application;

import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.student.domain.Student;

public interface EnrollmentCreateValidate {
    void validate(Course course, Student student);
}
