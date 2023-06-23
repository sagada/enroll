package com.sugang.app.api.service.enroll.response;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class EnrollServiceResponse {
    private Long courseId;
    private String courseName;
    private Long studentId;
    private String studentName;
    private Long enrollId;
    private LocalDateTime registerDateTime;

    @Builder
    private EnrollServiceResponse(Long courseId, String courseName, Long studentId, String studentName, Long enrollId, LocalDateTime registerDateTime) {
        this.courseId = courseId;
        this.courseName = courseName;
        this.studentId = studentId;
        this.studentName = studentName;
        this.enrollId = enrollId;
        this.registerDateTime = registerDateTime;
    }
}
