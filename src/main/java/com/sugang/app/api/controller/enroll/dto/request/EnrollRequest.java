package com.sugang.app.api.controller.enroll.dto.request;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
public class EnrollRequest {

    @NotNull(message = "수업 ID는 필수입니다.")
    private long courseId;

    @NotNull(message = "학생 ID는 필수입니다.")
    private long studentId;

    @Builder
    private EnrollRequest(long courseId, long studentId)
    {
        this.courseId = courseId;
        this.studentId = studentId;
    }
}
