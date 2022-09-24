package com.sugang.toys.command.student.domain.exception;

public class StudentException extends RuntimeException{

    public StudentException(StudentErrorCode message)
    {
        super(message.getMessage());
    }
}
