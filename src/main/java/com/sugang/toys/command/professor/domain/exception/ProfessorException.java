package com.sugang.toys.command.professor.domain.exception;

import com.sugang.toys.command.common.exception.ErrorCode;

public class ProfessorException extends RuntimeException{

    public ProfessorException(ErrorCode errorCode)
    {
        super(errorCode.getMessage());
    }
}
