package com.sugang.app.api.controller.course.dto.request;

import com.sugang.app.domain.course.CourseSchedule;
import lombok.Builder;
import lombok.EqualsAndHashCode;
import lombok.Getter;

import java.time.LocalDateTime;

@EqualsAndHashCode(of = {"week"})
@Getter
public class CourseScheduleRequest {
    private final int week;
    private final LocalDateTime from;
    private final LocalDateTime to;
    private final String roomNumber;

    @Builder
    private CourseScheduleRequest(LocalDateTime from, LocalDateTime to, String roomNumber, int week)
    {
        this.week = week;
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
