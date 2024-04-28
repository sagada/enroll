package com.sugang.app.api.controller.course;

import com.sugang.app.api.controller.course.dto.request.CourseCreateRequest;
import com.sugang.app.api.controller.course.dto.response.CreatedCourseResponse;
import com.sugang.app.api.service.course.CreateCourseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseController {

    private final CreateCourseService createCourseService;

    @PostMapping
    public ResponseEntity<CreatedCourseResponse> createCourse(@Valid @RequestBody CourseCreateRequest courseCreateRequest)
    {
        CreatedCourseResponse course = createCourseService.createCourse(courseCreateRequest.toServiceDto());
        return ResponseEntity.ok(course);
    }
}
