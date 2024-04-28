package com.sugang.app.domain.course;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


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
