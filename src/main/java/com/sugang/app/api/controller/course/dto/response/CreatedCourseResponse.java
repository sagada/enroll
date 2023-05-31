package com.sugang.app.api.controller.course.dto.response;

import com.sugang.app.domain.course.Course;
import com.sugang.app.domain.course.CourseSchedule;
import com.sugang.app.domain.course.CourseSchedules;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class CreatedCourseResponse {

    private final String courseName;
    private final Long courseId;
    private final Set<CourseScheduleResult> courseScheduleResultSet;
    private final Long professorId;

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
    static class CourseScheduleResult
    {
        private String startTime;
        private String endTime;
    }

    public static CreatedCourseResponse from(Course save)
    {
        CourseSchedules courseSchedules = save.getCourseSchedules();
        Set<CourseSchedule> courseScheduleSet = courseSchedules.getCourseScheduleSet();
        Set<CourseScheduleResult> courseScheduleResultSet = courseScheduleSet.stream()
                .map(courseSchedule -> new CourseScheduleResult(courseSchedule.getStart().toString(), courseSchedule.getEnd().toString()))
                .collect(Collectors.toSet());

        return new CreatedCourseResponse(
                save.getCourseName().getValue()
                , save.getId()
                , courseScheduleResultSet
                , save.getId()
        );
    }
}
