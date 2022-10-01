package com.sugang.toys.command.common.domain.exception;

import com.sugang.toys.command.common.exception.ErrorCode;

public class CommonException extends RuntimeException{

    public CommonException(ErrorCode errorCode)
    {
        super(errorCode.getMessage());
    }
}
