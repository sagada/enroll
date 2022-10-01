package com.sugang.toys.command.course.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Getter
@Embeddable
@EqualsAndHashCode(of = {"value"})
public class CourseName {
    private String value;

    // TODO RuntimeException 제거
    public CourseName(String value)
    {
        if (value == null || value.isBlank())
        {
            throw new RuntimeException("과목 이름 에러");
        }

        this.value = value;
    }
}
