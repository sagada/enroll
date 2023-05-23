package com.sugang.app.api.service.course;

import com.sugang.app.api.service.course.request.CourseCreateServiceRequest;
import com.sugang.app.api.controller.course.dto.response.CreatedCourseResponse;

public interface CreateCourseService {
    CreatedCourseResponse createCourse(CourseCreateServiceRequest courseCreateRequest);
}
