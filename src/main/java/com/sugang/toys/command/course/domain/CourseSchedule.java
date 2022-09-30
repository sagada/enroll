package com.sugang.toys.command.course.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Getter
@NoArgsConstructor
public class CourseSchedule {
    private String day;
    private LocalDate from;
    private LocalDate to;

    public CourseSchedule(String day, LocalDate from, LocalDate to)
    {
        this.day = day;
        this.from = from;
        this.to = to;
    }
}
