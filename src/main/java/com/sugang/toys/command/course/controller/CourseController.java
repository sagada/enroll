package com.sugang.toys.command.course.controller;

import com.sugang.toys.command.course.application.CreateCourseService;
import com.sugang.toys.command.course.application.dto.CourseCreateCommand;
import com.sugang.toys.command.course.application.dto.CreatedCourseResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CreateCourseService createCourseService;

    @Autowired
    public CourseController(CreateCourseService createCourseService)
    {
        this.createCourseService = createCourseService;
    }

    @PostMapping
    public Long createCourse(CourseCreateCommand courseCreateCommand)
    {
        CreatedCourseResult course = createCourseService.createCourse(courseCreateCommand);

        Long courseId = course.getCourseId();
        log.info("created CourseId : {}", courseId);

        return courseId;
    }
}
