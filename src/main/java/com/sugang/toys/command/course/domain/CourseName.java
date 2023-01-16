package com.sugang.toys.command.course.domain;

import com.sugang.toys.command.common.exception.ErrorCode;
import com.sugang.toys.command.course.domain.exception.CourseException;
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
            throw new CourseException(ErrorCode.COURSE_NAME_BLANK);
        }

        this.value = value;
    }
}
