package com.sugang.toys.command.course.application.dto;

import com.sugang.toys.command.course.domain.CourseSchedule;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Getter
public class CourseScheduleRequest {
    private final DayOfWeek day;
    private final LocalDateTime from;
    private final LocalDateTime to;
    private final String roomNumber;

    public CourseScheduleRequest(DayOfWeek day, LocalDateTime from, LocalDateTime to, String roomNumber)
    {
        this.day = day;
        this.from = from;
        this.to = to;
        this.roomNumber = roomNumber;
    }

    public static CourseSchedule from(CourseScheduleRequest courseScheduleDto)
    {
        return new CourseSchedule(
                 courseScheduleDto.getFrom()
                , courseScheduleDto.getTo()
                , courseScheduleDto.getRoomNumber()
        );
    }
}
