package com.sugang.app.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public enum ErrorCode {

    //#################### COMMON ####################
    NOT_FOUND_RESOURSE("없는 리소스", HttpStatus.NOT_FOUND),
    SERVER_ERROR("서버 에러", HttpStatus.INTERNAL_SERVER_ERROR),
    //#################### STUDENT ####################
    EXCEEDED_COURSE_CREDIT("수강 가능 학점 초과", HttpStatus.BAD_REQUEST),

    // ################### COURSE ###################
    DUPLICATE_COURSE_NAME("중복되는 수업 이름", HttpStatus.BAD_REQUEST),
    INVALID_COURSE_SCORE("학점 설정 오류", HttpStatus.BAD_REQUEST),
    EXCEEDED_COURSE_STUDENT("수강 가능 학생 초과", HttpStatus.BAD_REQUEST),
    DUPLICATE_COURSE("중복 수강 신청", HttpStatus.BAD_REQUEST),
    CLOSED_COURSE("닫힌 수강", HttpStatus.BAD_REQUEST),
    DUPLICATE_COURSE_TIME("수강 시간 겹침", HttpStatus.BAD_REQUEST),

    ;
    private final String message;
    private final HttpStatus httpStatus;

    ErrorCode(String message, HttpStatus httpStatus)
    {
        this.message = message;
        this.httpStatus = httpStatus;
    }
}
