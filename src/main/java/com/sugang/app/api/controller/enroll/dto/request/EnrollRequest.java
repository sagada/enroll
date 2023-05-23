package com.sugang.app.api.controller.enroll.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EnrollRequest {
    private long courseId;
    private long studentId;

    @Builder
    private EnrollRequest(long courseId, long studentId)
    {
        this.courseId = courseId;
        this.studentId = studentId;
    }
}
