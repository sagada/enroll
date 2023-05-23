package com.sugang.app.global.exception;

import com.sugang.app.global.config.db.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(value = {ApiException.class})
    public ResponseEntity<ErrorResponse> handleDomainException(ApiException exception)
    {
        log.error("handle DomainException : {}", exception.getErrorCode());
        return ErrorResponse.response(exception.getErrorCode());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(value = BindException.class)
    public ResponseEntity<ErrorResponse> handleBindException(BindException exception)
    {
        String allErrorMessage = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> "[" + error.getField() + "] - "+   error.getDefaultMessage())
                .collect(Collectors.joining("\n"));
        log.error("handle bindException : {}", allErrorMessage);

        return ErrorResponse.badRequest(allErrorMessage);
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception exception)
    {
        log.error("handle Exception : {}", exception.getMessage());
        return ErrorResponse.response(ErrorCode.SERVER_ERROR);
    }
}
