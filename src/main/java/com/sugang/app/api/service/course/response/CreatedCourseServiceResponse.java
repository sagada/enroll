package com.sugang.app.api.service.course.response;

import com.sugang.app.domain.course.Course;
import com.sugang.app.domain.course.CourseSchedule;
import lombok.*;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

// TODO controller -> service
@Getter
@ToString
@Setter
@NoArgsConstructor
public class CreatedCourseServiceResponse {

    private String courseName;
    private Long courseId;
    private Set<CourseScheduleResult> courseScheduleResultSet;
    private Long professorId;

    public CreatedCourseServiceResponse(
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
        private LocalDateTime startTime;
        private LocalDateTime endTime;
    }

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
