package com.sugang.toys.command.student.controller;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class StudentEnterCommand {

    private String name;
    private String birthDay;

    public StudentEnterCommand(String name, String birthDay)
    {
        this.name = name;
        this.birthDay = birthDay;
    }
}
