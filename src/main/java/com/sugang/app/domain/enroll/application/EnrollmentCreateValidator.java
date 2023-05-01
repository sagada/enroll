package com.sugang.app.domain.enroll.application;

public interface EnrollmentCreateValidator {
    void validate(Long courseId, Long studentId);
}
