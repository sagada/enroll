package com.sugang.app.api.controller.enroll;

import com.sugang.app.api.controller.enroll.dto.request.EnrollRequest;
import com.sugang.app.api.controller.enroll.dto.response.EnrollResponse;
import com.sugang.app.api.service.enroll.EnrollService;
import com.sugang.app.api.service.enroll.response.EnrollServiceResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/enroll")
public class EnrollmentController {

    private final EnrollService enrollService;

    @PostMapping
    public EnrollResponse enroll(@Valid @RequestBody EnrollRequest enrollRequest)
    {
        EnrollServiceResponse enroll = enrollService.enroll(enrollRequest.courseId(), enrollRequest.studentId());
        return EnrollResponse.from(enroll);
    }


}