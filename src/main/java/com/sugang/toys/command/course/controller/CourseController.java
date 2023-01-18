package com.sugang.toys.command.course.controller;

import com.sugang.toys.command.course.application.CreateCourseService;
import com.sugang.toys.command.course.application.dto.CourseCreateCommand;
import com.sugang.toys.command.course.application.dto.CreatedCourseResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/course")
public class CourseController {

    private final CreateCourseService createCourseService;

    @PostMapping
    public Long createCourse(@RequestBody CourseCreateCommand courseCreateCommand)
    {
        CreatedCourseResult course = createCourseService.createCourse(courseCreateCommand);
        log.info("created CourseId : {}", course.getCourseId());
        return course.getCourseId();
    }
}
