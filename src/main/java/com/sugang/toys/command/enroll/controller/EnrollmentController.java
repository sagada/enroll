package com.sugang.toys.command.enroll.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.BufferedReader;
import java.io.InputStreamReader;

@RequestMapping("/enroll")
public class EnrollmentController {

    @PostMapping
    public void enroll(@RequestBody EnrollMentStudent student)
    {

    }
}
