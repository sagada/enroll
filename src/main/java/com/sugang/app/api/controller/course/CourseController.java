package com.sugang.app.api.controller.course;

import com.sugang.app.api.controller.course.dto.request.CourseCreateRequest;
import com.sugang.app.api.service.course.CreateCourseService;
import com.sugang.app.api.controller.course.dto.response.CreatedCourseResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseController {

    private final CreateCourseService createCourseService;

    @PostMapping
    public ResponseEntity<Long> createCourse(@Valid @RequestBody CourseCreateRequest courseCreateRequest)
    {
        CreatedCourseResponse course = createCourseService.createCourse(courseCreateRequest.toServiceDto());
        return ResponseEntity.ok(course.getCourseId());
    }
}
