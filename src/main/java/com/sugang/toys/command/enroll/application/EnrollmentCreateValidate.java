package com.sugang.toys.command.enroll.application;

import com.sugang.toys.command.enroll.domain.Enrollment;

public interface EnrollmentCreateValidate {
    void validate(Enrollment enrollment);
}
