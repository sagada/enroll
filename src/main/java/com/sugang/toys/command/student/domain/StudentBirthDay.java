package com.sugang.toys.command.student.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.Table;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class StudentBirthDay {

    private String value;

    public StudentBirthDay(String birthDay)
    {
        this.value = birthDay;
    }

    public String value()
    {
        return this.value;
    }
}