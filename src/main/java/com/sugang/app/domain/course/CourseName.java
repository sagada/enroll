package com.sugang.app.domain.course;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor
@Getter
@Embeddable
@EqualsAndHashCode(of = {"value"})
public class CourseName {

    @Column(name = "course_name")
    private String value;

    public CourseName(String value)
    {
        if (value.isBlank())
        {
            throw new IllegalStateException("course name empty");
        }

        this.value = value;
    }
}
