package com.sugang.app.api.controller.enroll;

import com.sugang.app.api.controller.enroll.dto.request.EnrollRequest;
import com.sugang.app.api.service.enroll.EnrollmentCreateService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/enroll")
public class EnrollmentController {
    private final EnrollmentCreateService enrollmentCreateService;

    @PostMapping
    public void enroll(@Valid @RequestBody EnrollRequest enrollRequest)
    {
        enrollmentCreateService.enroll(enrollRequest.getCourseId(), enrollRequest.getStudentId());
    }
}
