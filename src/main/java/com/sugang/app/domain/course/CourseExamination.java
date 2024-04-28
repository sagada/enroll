package com.sugang.app.domain.course;

import com.sugang.app.domain.course.exception.CourseException;
import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;

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
