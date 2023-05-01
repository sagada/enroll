package com.sugang.app.domain.course.application;

import com.sugang.app.domain.course.application.dto.CourseCreateCommand;
import com.sugang.app.domain.course.application.dto.CreatedCourseResult;

public interface CreateCourseService {
    CreatedCourseResult createCourse(CourseCreateCommand courseCreateCommand);
}
