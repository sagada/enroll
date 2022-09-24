package com.sugang.toys.command.enroll.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/enroll")
public class EnrollmentController {

    @GetMapping("/a")
    public void ddd(@RequestBody EnrollMentStudent student)
    {

    }
}
