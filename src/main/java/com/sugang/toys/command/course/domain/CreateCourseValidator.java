package com.sugang.toys.command.course.domain;

import java.util.Set;

public interface CreateCourseValidator {
    void validate(
            Long professorId
            , String courseName
            , Set<CourseSchedule> openCourseScheduleSet
    );
}
