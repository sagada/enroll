package com.sugang.toys.command.course.application;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseController {

    private final OpenCourseService openCourseService;
    private final CloseCourseService closeCourseService;
    @PostMapping("/open")
    public ResponseEntity<Long> openCourse(@RequestBody OpenCourseRequest openCourseRequest)
    {
        return ResponseEntity.ok(openCourseService.openCourse(openCourseRequest));
    }

    @PostMapping("/close/{courseId}")
    public ResponseEntity<Void> closeCourse(@PathVariable Long courseId)
    {
        closeCourseService.close(courseId);
        return ResponseEntity.ok(null);
    }
}
