package com.sugang.toys.command.course.application.dto;

import com.sugang.toys.command.course.domain.CourseSchedule;
import lombok.Getter;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Getter
public class CourseScheduleRequest {
    private DayOfWeek day;
    private LocalDateTime from;
    private LocalDateTime to;
    private String roomNumber;

    public CourseScheduleRequest(DayOfWeek day, LocalDateTime from, LocalDateTime to, String roomNumber)
    {
        this.day = day;
        this.from = from;
        this.to = to;
        this.roomNumber = roomNumber;
    }

    public static CourseSchedule convert(CourseScheduleRequest courseScheduleDto)
    {
        return new CourseSchedule(
                courseScheduleDto.getDay()
                , courseScheduleDto.getFrom()
                , courseScheduleDto.getTo()
                , courseScheduleDto.getRoomNumber()
        );
    }
}
