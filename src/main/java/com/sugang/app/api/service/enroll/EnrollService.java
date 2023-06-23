package com.sugang.app.api.service.enroll;

import com.sugang.app.api.service.enroll.response.EnrollServiceResponse;

public interface EnrollService {
    EnrollServiceResponse enroll(long courseId, long studentId);
}
