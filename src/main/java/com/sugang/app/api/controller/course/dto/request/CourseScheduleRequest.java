package com.sugang.app.api.controller.course.dto.request;

import com.sugang.app.domain.course.CourseSchedule;
import lombok.Builder;

import java.time.LocalDateTime;

@Builder
public record CourseScheduleRequest(
        int week,
        LocalDateTime from,
        LocalDateTime to,
        String roomNumber
) {
    public static CourseSchedule from(CourseScheduleRequest courseScheduleDto)
    {
        return new CourseSchedule(
                 courseScheduleDto.from()
                , courseScheduleDto.to()
                , courseScheduleDto.roomNumber()
        );
    }
}
