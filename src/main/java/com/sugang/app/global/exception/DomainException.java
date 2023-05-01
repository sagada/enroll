package com.sugang.app.global.exception;

import com.sugang.app.global.config.db.ErrorCode;
import lombok.Getter;

@Getter
public class DomainException extends RuntimeException{
    private final ErrorCode errorCode;

    public DomainException(ErrorCode errorCode)
    {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }
}
