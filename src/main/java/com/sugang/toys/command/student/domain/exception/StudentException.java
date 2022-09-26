package com.sugang.toys.command.student.domain.exception;

import com.sugang.toys.command.common.exception.ErrorCode;

public class StudentException extends RuntimeException{

    public StudentException(ErrorCode message)
    {
        super(message.getMessage());
    }
}
