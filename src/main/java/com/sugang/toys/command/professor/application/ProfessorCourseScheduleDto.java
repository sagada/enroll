package com.sugang.toys.command.professor.application;

import com.sugang.toys.command.course.domain.CourseSchedule;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class ProfessorCourseScheduleDto {
    private DayOfWeek day;
    private LocalDateTime from;
    private LocalDateTime to;
    private String roomNumber;

    public static CourseSchedule convert(ProfessorCourseScheduleDto professorCourseScheduleDto)
    {
        return new CourseSchedule(
                professorCourseScheduleDto.getFrom()
                , professorCourseScheduleDto.getTo()
                , professorCourseScheduleDto.getRoomNumber()
        );
    }

    public ProfessorCourseScheduleDto(DayOfWeek day, LocalDateTime from, LocalDateTime to, String roomNumber)
    {
        this.day = day;
        this.from = from;
        this.to = to;
        this.roomNumber = roomNumber;
    }
}
