package com.sugang.toys.command.enroll.controller;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Data
public class EnrollMentStudent {

    @Min(1)
    @Max(4)
    private Integer grade;
}
