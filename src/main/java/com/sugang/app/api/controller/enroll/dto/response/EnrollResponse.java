package com.sugang.app.api.controller.enroll.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sugang.app.api.service.enroll.response.EnrollServiceResponse;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@NoArgsConstructor
@Getter
@Setter
public class EnrollResponse {

    private Long courseId;
    private String courseName;
    private Long studentId;
    private String studentName;
    private Long enrollId;

    @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime registerDateTime;

    @Builder
    private EnrollResponse(
            Long courseId,
            String courseName,
            Long studentId,
            String studentName,
            Long enrollId,
            LocalDateTime registerDateTime)
    {
        this.courseId = courseId;
        this.courseName = courseName;
        this.studentId = studentId;
        this.studentName = studentName;
        this.enrollId = enrollId;
        this.registerDateTime = registerDateTime;
    }

    public static EnrollResponse from(EnrollServiceResponse serviceResponse)
    {
        return EnrollResponse.builder()
                .enrollId(serviceResponse.getEnrollId())
                .courseName(serviceResponse.getCourseName())
                .courseId(serviceResponse.getCourseId())
                .studentId(serviceResponse.getStudentId())
                .studentName(serviceResponse.getStudentName())
                .registerDateTime(serviceResponse.getRegisterDateTime())
                .build();
    }
}
