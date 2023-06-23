package com.sugang.app.api.controller.student.dto.request;

import com.sugang.app.api.service.student.request.CreateStudentServiceDto;
import lombok.Builder;
import lombok.Getter;

@Getter
public class CreateStudentDto {

    private final Long departmentId;
    private final String name;
    private final String email;
    private final int score;
    private final Integer academicYear;
    private final Long advisorProfessorId;

    @Builder
    public CreateStudentDto(
            Long departmentId,
            String name,
            String email,
            int score,
            Integer academicYear,
            Long advisorProfessorId)
    {
        this.departmentId = departmentId;
        this.name = name;
        this.email = email;
        this.score = score;
        this.academicYear = academicYear;
        this.advisorProfessorId = advisorProfessorId;
    }

    public CreateStudentServiceDto toServiceDto()
    {
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
