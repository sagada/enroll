package com.sugang.toys.command.course.application;

import com.sugang.toys.command.course.application.dto.CourseCreateCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CreateCourseController {

    private final OpenCourseService openCourseService;

    @Autowired
    public CreateCourseController(OpenCourseService openCourseService)
    {
        this.openCourseService = openCourseService;
    }

    @PostMapping
    public Long createCourse(CourseCreateCommand courseCreateCommand)
    {
        return openCourseService.createCourse(courseCreateCommand);
    }

    @PutMapping("/open/{courseId}")
    public void openCourse(@PathVariable Long courseId)
    {
        openCourseService.openCourse(courseId);
    }
}
