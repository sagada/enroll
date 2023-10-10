package com.sugang.app.api.controller.course.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sugang.app.domain.course.Course;
import com.sugang.app.domain.course.CourseSchedule;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record CreatedCourseResponse(
        String courseName,
        Long courseId,
        Set<CourseScheduleResult> courseScheduleResultSet,
        Long professorId
) {
    public record CourseScheduleResult(
            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            LocalDateTime startTime,

            @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
            LocalDateTime endTime
    ) {}

    public static CreatedCourseResponse from(Course save) {
        Set<CourseSchedule> courseScheduleSet = save.getCourseScheduleSet();
        Set<CourseScheduleResult> courseScheduleResultSet = courseScheduleSet.stream()
                .map(courseSchedule -> new CourseScheduleResult(courseSchedule.getStart(), courseSchedule.getEnd()))
                .collect(Collectors.toSet());

        return new CreatedCourseResponse(
                save.getCourseName().getValue()
                , save.getId()
                , courseScheduleResultSet
                , save.getId()
        );
    }
}
