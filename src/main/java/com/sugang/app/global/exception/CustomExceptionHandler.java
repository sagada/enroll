package com.sugang.app.global.exception;

import com.sugang.app.global.config.db.ErrorCode;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(value = {DomainException.class})
    public ResponseEntity<ErrorResponse> handleDomainException(DomainException exception)
    {
        log.error("handle DomainException : {}", exception.getErrorCode());
        return ErrorResponse.response(exception.getErrorCode());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception)
    {
        log.error("handle MethodArgumentNotValidException : {}", exception.getMessage());

        BindingResult bindingResult = exception.getBindingResult();

        String errorList = bindingResult.getFieldErrors()
                .stream()
                .map(fieldError -> "[" + fieldError.getField() + "] Error " + fieldError.getDefaultMessage() + " : " + fieldError.getRejectedValue())
                .collect(Collectors.joining("\n"));

        return ErrorResponse.badRequest(errorList);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<ErrorResponse> exceptionHandler(Exception exception)
    {
        log.error("handle Server Error : {}", exception.getMessage());
        return ErrorResponse.response(ErrorCode.SERVER_ERROR);
    }
}
