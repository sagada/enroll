package com.sugang.toys.command.course.application;

import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.course.domain.CourseSchedule;
import com.sugang.toys.command.course.domain.CourseSchedules;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@Accessors(chain = true)
public class CreatedCourseResult {

    private final String courseName;
    private final Long courseId;
    private final Set<CourseScheduleResult> courseScheduleResultSet;
    private final Long professorId;
    private final String professorName;

    public CreatedCourseResult(
            String courseName
            , Long courseId
            , Set<CourseScheduleResult> courseScheduleResultSet
            , Long professorId
            , String professorName)
    {
        this.courseName = courseName;
        this.courseId = courseId;
        this.courseScheduleResultSet = courseScheduleResultSet;
        this.professorId = professorId;
        this.professorName = professorName;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    static class CourseScheduleResult
    {
        private String startTime;
        private String endTime;
    }

    public static CreatedCourseResult from(Course save)
    {
        CourseSchedules courseSchedules = save.getCourseSchedules();
        Set<CourseSchedule> courseScheduleSet = courseSchedules.getCourseScheduleSet();
        Set<CourseScheduleResult> courseScheduleResultSet = courseScheduleSet.stream()
                .map(courseSchedule -> new CourseScheduleResult(courseSchedule.getStart().toString(), courseSchedule.getEnd().toString()))
                .collect(Collectors.toSet());

        return new CreatedCourseResult(
                save.getName()
                , save.getId()
                , courseScheduleResultSet
                , save.getProfessor().getId()
                , save.getProfessor().getName()
        );

    }
}
