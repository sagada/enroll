package com.sugang.toys.command.course.domain.exception;

import com.sugang.toys.command.common.exception.ErrorCode;

public class CourseException extends RuntimeException{
    public CourseException(ErrorCode errorCode) {
        super(errorCode.getMessage());
    }

    public CourseException(String message)
    {
        super(message);
    }
}
