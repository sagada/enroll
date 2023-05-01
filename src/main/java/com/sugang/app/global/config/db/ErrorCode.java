package com.sugang.app.global.config.db;


import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@AllArgsConstructor
@Getter
public enum ErrorCode {
    STUDENT_NOT_FOUND(HttpStatus.NOT_IMPLEMENTED, "없는 학생입니다."),
    SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류");

    private final HttpStatus httpStatus;
    private final String message;


}
