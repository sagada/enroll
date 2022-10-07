package com.sugang.toys.command.course.application;

import lombok.Data;

import java.util.Set;

@Data
public class CourseCreateRequest {
    private String courseName;
    private Long departmentId;
    private Set<CourseScheduleDto> courseScheduleSet;
    private Integer maxCourseStudentCount;
}
