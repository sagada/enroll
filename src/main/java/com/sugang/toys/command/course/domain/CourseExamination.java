package com.sugang.toys.command.course.domain;

import com.sugang.toys.command.course.domain.exception.CourseException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Getter
@Embeddable
@NoArgsConstructor
public class CourseExamination {
    private LocalDateTime midTermDate;
    private LocalDateTime finalTermDate;

    public CourseExamination(LocalDateTime midTermDate, LocalDateTime finalTermDate)
    {
        if (midTermDate.isAfter(finalTermDate))
        {
            throw new CourseException("examination date error");
        }

        this.midTermDate = midTermDate;
        this.finalTermDate = finalTermDate;
    }
}
