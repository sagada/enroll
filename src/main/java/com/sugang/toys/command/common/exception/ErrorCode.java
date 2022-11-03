package com.sugang.toys.command.common.exception;

import lombok.Getter;

@Getter
public enum ErrorCode {

    //#################### STUDENT ####################
    EXPEL_UPDATE_ERROR("퇴학인 학생은 업데이트가 불가능합니다.", 101),
    GRADUATION_UPDATE_ERROR("졸업한 학생은 업데이트가 불가능합니다.", 102),
    EXCEED_GRADE_UPDATE_ERROR("4학년 초과", 103),
    NONE_STUDENT("없는 학생입니다.", 104),
    ASSIGN_DEPART_MENT_ERROR("입학인 학생만 할당 가능", 105),
    //#################### PROFESSOR ####################
    NONE_PROFESSOR("없는 교수입니다.", 200),

    // ################### COURSE ###################
    NONE_COURSE("없는 수업입니다.", 300),
    DUPLICATE_COURSE_NAME("중복되는 수업 이름이 있습니다.", 301),
    COURSE_NAME_BLANK("이름이 빈값입니다.", 302),
    COURSE_SUMMARY_ERROR("수업 요약이 에러", 303)
    , INTERNAL_LOGIC_ERROR("로직 에러", 304);
    private final String message;
    private final Integer code;

    ErrorCode(String message, Integer code)
    {
        this.message = message;
        this.code = code;
    }
}
