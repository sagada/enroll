package com.sugang.toys.command.student.application;

import lombok.Getter;

@Getter
public class CreateStudentDto {

    private Long departmentId;
    private String name;
    private String email;
    private String score;
    private Integer academicYear;
    private Long advisorProfessorId;
}
