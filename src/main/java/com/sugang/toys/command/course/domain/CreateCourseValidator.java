package com.sugang.toys.command.course.domain;

import com.sugang.toys.command.professor.domain.Professor;

import java.util.Set;

public interface CreateCourseValidator {
    void validate(
            Professor professor
            , String courseName
            , Set<CourseSchedule> openCourseScheduleSet);
}
