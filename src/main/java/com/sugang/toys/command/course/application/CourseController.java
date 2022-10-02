package com.sugang.toys.command.course.application;

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

    private final OpenCourseService openCourseService;

    @PostMapping("/open")
    public ResponseEntity<Long> openCourse(@RequestBody OpenCourseRequest openCourseRequest)
    {
        return ResponseEntity.ok(openCourseService.openCourse(openCourseRequest));
    }
}
