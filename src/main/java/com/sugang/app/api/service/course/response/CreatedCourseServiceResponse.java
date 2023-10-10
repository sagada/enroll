package com.sugang.app.api.service.course.response;

import com.sugang.app.domain.course.Course;
import com.sugang.app.domain.course.CourseSchedule;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

public record CreatedCourseServiceResponse(
        String courseName,
        Long courseId,
        Set<CourseScheduleResult> courseScheduleResultSet,
        Long professorId
) {
    @Builder
    public record CourseScheduleResult(
            LocalDateTime startTime,
            LocalDateTime endTime
    ) {}

    public static CreatedCourseServiceResponse from(Course save)
    {
        Set<CourseSchedule> courseScheduleSet = save.getCourseScheduleSet();
        Set<CourseScheduleResult> courseScheduleResultSet = courseScheduleSet.stream()
                .map(courseSchedule -> new CourseScheduleResult(courseSchedule.getStart(), courseSchedule.getEnd()))
                .collect(Collectors.toSet());

        return new CreatedCourseServiceResponse(
                save.getCourseName().getValue()
                , save.getId()
                , courseScheduleResultSet
                , save.getId()
        );
    }
}
