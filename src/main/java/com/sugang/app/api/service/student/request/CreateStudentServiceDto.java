package com.sugang.app.api.service.student.request;

import lombok.Builder;

@Builder
public record CreateStudentServiceDto(
        Long departmentId,
        String name,
        String email,
        int score,
        Integer academicYear,
        Long advisorProfessorId
) {}
