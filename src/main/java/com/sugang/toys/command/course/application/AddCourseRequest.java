package com.sugang.toys.command.course.application;

import com.sugang.toys.command.course.domain.CourseSchedule;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Set;

@Data
public class AddCourseRequest {
    private Long professorId;
    private String courseName;
    private Long departmentId;
    private Set<CourseScheduleDto> courseScheduleSet;
    private Integer maxCourseStudentCount;

    @Data
    static class CourseScheduleDto
    {
        private DayOfWeek day;
        private LocalDateTime from;
        private LocalDateTime to;
        private String roomNumber;

        public static CourseSchedule convert(CourseScheduleDto courseScheduleDto)
        {
            return new CourseSchedule(
                    courseScheduleDto.getDay()
                    , courseScheduleDto.getFrom()
                    , courseScheduleDto.getTo()
                    , courseScheduleDto.getRoomNumber()
            );
        }
        public CourseScheduleDto(DayOfWeek day, LocalDateTime from, LocalDateTime to, String roomNumber) {
            this.day = day;
            this.from = from;
            this.to = to;
            this.roomNumber = roomNumber;
        }
    }

}
