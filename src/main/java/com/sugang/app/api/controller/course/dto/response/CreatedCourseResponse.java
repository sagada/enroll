package com.sugang.app.api.controller.course.dto.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.sugang.app.domain.course.Course;
import com.sugang.app.domain.course.CourseSchedule;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@ToString
@Setter
@NoArgsConstructor
public class CreatedCourseResponse {

    private  String courseName;
    private  Long courseId;
    private  Set<CourseScheduleResult> courseScheduleResultSet;
    private  Long professorId;

    public CreatedCourseResponse(
            String courseName
            , Long courseId
            , Set<CourseScheduleResult> courseScheduleResultSet
            , Long professorId)
    {
        this.courseName = courseName;
        this.courseId = courseId;
        this.courseScheduleResultSet = courseScheduleResultSet;
        this.professorId = professorId;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class CourseScheduleResult
    {
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime startTime;

        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        private LocalDateTime endTime;
    }

    public static CreatedCourseResponse from(Course save)
    {
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
