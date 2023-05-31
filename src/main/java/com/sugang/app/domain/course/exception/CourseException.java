package com.sugang.app.domain.course.exception;

import com.sugang.app.global.common.exception.ErrorCode;

public class CourseException extends RuntimeException{
    public CourseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }

    public CourseException(String message)
    {
        super(message);
    }
}
