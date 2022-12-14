package com.sugang.toys.command.course.application;

import com.sugang.toys.command.course.application.dto.CourseCreateCommand;
import com.sugang.toys.command.course.application.dto.CreatedCourseResult;

public interface CreateCourseService {
    CreatedCourseResult createCourse(CourseCreateCommand courseCreateCommand);
}
