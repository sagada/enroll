package com.sugang.toys.command.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    //#################### STUDENT ####################
    EXPEL_UPDATE_ERROR("퇴학인 학생은 업데이트가 불가능합니다.", 101),
    GRADUATION_UPDATE_ERROR("졸업한 학생은 업데이트가 불가능합니다.", 102),
    EXCEED_GRADE_UPDATE_ERROR("4학년 초과", 103),
    NONE_STUDENT("없는 학생입니다.", 104),
    ASSIGN_DEPART_MENT_ERROR("입학인 학생만 할당 가능", 105)
    //#################### PROFESSOR ####################

    ;
    private final String message;
    private final Integer code;

    ErrorCode(String message, Integer code)
    {
        this.message = message;
        this.code = code;
    }
}
