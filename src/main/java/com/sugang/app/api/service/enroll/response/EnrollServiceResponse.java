package com.sugang.app.api.service.enroll.response;

import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EnrollServiceResponse(
        Long courseId,
        String courseName,
        Long studentId,
        String studentName,
        Long enrollId,
        LocalDateTime registerDateTime
) {}
