package com.sugang.app.api.controller.course.dto.request;

import com.sugang.app.domain.course.domain.CourseSchedule;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CourseScheduleRequest {
    private final LocalDateTime from;
    private final LocalDateTime to;
    private final String roomNumber;

    @Builder
    private CourseScheduleRequest(LocalDateTime from, LocalDateTime to, String roomNumber)
    {
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
