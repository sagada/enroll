package com.sugang.toys.command.student.controller;

import com.sugang.toys.command.student.application.StudentEnterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {
    private final StudentEnterService studentEnterService;

    @Autowired
    public StudentController(StudentEnterService studentEnterService)
    {
        this.studentEnterService = studentEnterService;
    }

    @PostMapping
    public void register(@RequestBody StudentEnterCommand command)
    {
        studentEnterService.enter(command);
    }
}
