package com.sugang.app.global.exception;

import com.sugang.app.global.config.db.ErrorCode;
import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@Getter
public class ErrorResponse {
    private String message;
    private int errorCode;
    private String errorType;

    @Builder
    public ErrorResponse(String message, int errorCode, String errorType)
    {
        this.message = message;
        this.errorType = errorType;
        this.errorCode = errorCode;
    }

    public static ResponseEntity<ErrorResponse> response(ErrorCode errorCode)
    {
        return ResponseEntity
                .status(errorCode.getHttpStatus())
                .body(
                    ErrorResponse.builder()
                            .errorCode(errorCode.getHttpStatus().value())
                            .errorType(errorCode.getHttpStatus().getReasonPhrase())
                            .message(errorCode.getMessage())
                            .build()
                );
    }
    public static ResponseEntity<ErrorResponse> apiException(String message)
    {
        return ResponseEntity
                .status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(
                        ErrorResponse.builder()
                                .errorCode(HttpStatus.INTERNAL_SERVER_ERROR.value())
                                .errorType(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase())
                                .message(message)
                                .build()
                );
    }
    public static ResponseEntity<ErrorResponse> badRequest(String message)
    {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(
                        ErrorResponse.builder()
                                .errorCode(HttpStatus.BAD_REQUEST.value())
                                .errorType(HttpStatus.BAD_REQUEST.getReasonPhrase())
                                .message(message)
                                .build()
                );
    }
}
