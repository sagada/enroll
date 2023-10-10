package com.sugang.app.api.controller.enroll.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sugang.app.api.service.enroll.response.EnrollServiceResponse;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record EnrollResponse(
        Long courseId,
        String courseName,
        Long studentId,
        String studentName,
        Long enrollId,
        @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
        LocalDateTime registerDateTime
) {
    public static EnrollResponse from(EnrollServiceResponse serviceResponse)
    {
        return EnrollResponse.builder()
                .enrollId(serviceResponse.enrollId())
                .courseName(serviceResponse.courseName())
                .courseId(serviceResponse.courseId())
                .studentId(serviceResponse.studentId())
                .studentName(serviceResponse.studentName())
                .registerDateTime(serviceResponse.registerDateTime())
                .build();
    }
}
