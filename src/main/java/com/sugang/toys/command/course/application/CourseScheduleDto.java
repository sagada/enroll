package com.sugang.toys.command.course.application;

import com.sugang.toys.command.course.domain.CourseSchedule;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

public class CourseScheduleDto {
    private DayOfWeek day;
    private LocalDateTime from;
    private LocalDateTime to;
    private String roomNumber;

    public static CourseSchedule convert(CourseScheduleDto courseScheduleDto) {
    }
}
