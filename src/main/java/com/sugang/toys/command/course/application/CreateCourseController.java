package com.sugang.toys.command.course.application;

import com.sugang.toys.command.course.application.dto.CourseCreateCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CreateCourseController {

    private final CreateCourseService createCourseService;

    @Autowired
    public CreateCourseController(CreateCourseService createCourseService)
    {
        this.createCourseService = createCourseService;
    }

    @PostMapping
    public Long createCourse(CourseCreateCommand courseCreateCommand)
    {
        return createCourseService.createCourse(courseCreateCommand).getCourseId();
    }

//    @PutMapping("/open/{courseId}")
//    public void openCourse(@PathVariable Long courseId)
//    {
//        createCourseServiceImpl.openCourse(courseId);
//    }
}
