package com.sugang.toys.command.student.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Embeddable
public class StudentName {

    @Column(name = "name")
    private String value;

    public StudentName(String value)
    {
        if (value == null || value.isBlank())
        {
            throw new IllegalArgumentException("학생 이름 빈값 에러");
        }

        this.value = value;
    }

    public String value()
    {
        return this.value;
    }
}
