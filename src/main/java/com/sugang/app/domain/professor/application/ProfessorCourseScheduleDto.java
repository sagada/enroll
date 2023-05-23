package com.sugang.app.domain.professor.application;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
public class ProfessorCourseScheduleDto {
    private DayOfWeek day;
    private LocalDateTime from;
    private LocalDateTime to;
    private String roomNumber;
}
