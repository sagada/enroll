package com.sugang.app.global.exception;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ErrorResponse {
    private String message;
    private String errorCode;
    private int status;

    @Builder
    public ErrorResponse(String message, String errorCode, int status)
    {
        this.message = message;
        this.errorCode = errorCode;
        this.status = status;
    }

    public static ResponseEntity<ErrorResponse> response(ErrorCode errorCode)
    {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(
                    ErrorResponse.builder()
                            .status(errorCode.getHttpStatus().value())
                            .errorCode(errorCode.getHttpStatus().name())
                            .message(errorCode.getMessage())
                            .build()
                );
    }

    public static ResponseEntity<ErrorResponse> badRequest(String message)
    {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse.builder()
                                .errorCode(HttpStatus.BAD_REQUEST.name())
                                .status(HttpStatus.BAD_REQUEST.value())
                                .message(message)
                                .build()
                );
    }

}
