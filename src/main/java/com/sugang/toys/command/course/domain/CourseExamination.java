package com.sugang.toys.command.course.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor
public class CourseExamination {
    private String midTermDate;
    private String finalTermDate;

    public CourseExamination(String midTermDate, String finalTermDate)
    {
        this.midTermDate = midTermDate;
        this.finalTermDate = finalTermDate;
    }
}
