package com.sugang.toys.command.course.application.dto;

import lombok.Data;

import java.util.Set;

@Data
public class CourseCreateCommand {
    private String courseName;
    private Long departmentId;
    private Long professorId;
    private Set<CourseScheduleRequest> courseScheduleSet;
    private Integer maxCourseStudentCount;
}
