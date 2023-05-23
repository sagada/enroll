package com.sugang.app.api.controller.student;

import com.sugang.app.api.controller.student.dto.request.CreateStudentDto;
import com.sugang.app.api.service.student.StudentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/student")
public class StudentController {

    private final StudentService studentService;

    @PostMapping
    public void createStudent(@Valid @RequestBody CreateStudentDto createStudentDto)
    {
        studentService.save(createStudentDto.toServiceDto());
    }
}
