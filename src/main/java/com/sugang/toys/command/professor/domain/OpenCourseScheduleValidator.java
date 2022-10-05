package com.sugang.toys.command.professor.domain;

import com.sugang.toys.command.course.domain.CourseSchedule;

import java.util.Set;

public interface OpenCourseScheduleValidator {
    void openCourseScheduleCheck(Professor professor, Set<CourseSchedule> openCourseScheduleList);
}
