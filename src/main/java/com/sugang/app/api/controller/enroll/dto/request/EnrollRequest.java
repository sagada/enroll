package com.sugang.app.api.controller.enroll.dto.request;

import javax.validation.constraints.NotNull;

public record EnrollRequest(
    @NotNull(message = "수업 ID는 필수입니다.")
    Long courseId,

    @NotNull(message = "학생 ID는 필수입니다.")
    Long studentId
) {}
