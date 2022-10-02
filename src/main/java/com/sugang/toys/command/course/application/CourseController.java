package com.sugang.toys.command.course.application;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseController {

    private final CourseAddService courseAddService;

    @PostMapping("/add")
    public void addCourse(@RequestBody AddCourseRequest addCourseRequest)
    {
        courseAddService.addCourse(addCourseRequest);
    }
}
