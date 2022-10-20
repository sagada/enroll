package com.sugang.toys.command.course.application.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CourseCreateCommand {
    private String courseName;
    private Long departmentId;
    private Long professorId;
    private Set<CourseScheduleRequest> courseScheduleSet;

    public CourseCreateCommand(
            String courseName
            , Long departmentId
            , Long professorId
            , Set<CourseScheduleRequest> courseScheduleSet
    ) {
        this.courseName = courseName;
        this.departmentId = departmentId;
        this.professorId = professorId;
        this.courseScheduleSet = courseScheduleSet;
    }
}
