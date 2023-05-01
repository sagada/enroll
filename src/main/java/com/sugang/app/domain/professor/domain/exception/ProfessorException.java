package com.sugang.app.domain.professor.domain.exception;

import com.sugang.app.global.common.exception.ErrorCode;

public class ProfessorException extends RuntimeException{

    public ProfessorException(ErrorCode errorCode)
    {
        super(errorCode.getMessage());
    }
}
