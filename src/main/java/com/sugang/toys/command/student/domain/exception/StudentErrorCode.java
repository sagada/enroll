package com.sugang.toys.command.student.domain.exception;

import lombok.Getter;

@Getter
public enum StudentErrorCode {
    EXPEL_UPDATE_ERROR("퇴학인 학생은 업데이트가 불가능합니다."),
    GRADUATION_UPDATE_ERROR("졸업한 학생은 업데이트가 불가능합니다."),
    EXCEED_GRADE_UPDATE_ERROR("4학년 초과");

    private String message;

    StudentErrorCode(String message)
    {
        this.message = message;
    }
}
