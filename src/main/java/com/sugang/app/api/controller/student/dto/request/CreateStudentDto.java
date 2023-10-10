package com.sugang.app.api.controller.student.dto.request;

import com.sugang.app.api.service.student.request.CreateStudentServiceDto;
import lombok.Builder;

@Builder
public record CreateStudentDto(
        Long departmentId,
        String name,
        String email,
        int score,
        Integer academicYear,
        Long advisorProfessorId
) {

    public CreateStudentServiceDto toServiceDto() {
        return CreateStudentServiceDto.builder()
                .academicYear(academicYear)
                .name(name)
                .advisorProfessorId(advisorProfessorId)
                .departmentId(departmentId)
                .score(score)
                .email(email)
                .build();
    }
}
