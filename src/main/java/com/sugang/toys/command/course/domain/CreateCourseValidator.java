package com.sugang.toys.command.course.domain;

import java.util.Set;

public interface CreateCourseValidator {
    void validate(
            Long subjectId
            , Long professorId
            , Set<Long> preCourseIdSet
            , CourseName courseName
            , Set<CourseSchedule> openCourseScheduleSet
    );
}
