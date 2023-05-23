package com.sugang.app.api.service.student.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class CreateStudentServiceDto {
    private Long departmentId;
    private String name;
    private String email;
    private String score;
    private Integer academicYear;
    private Long advisorProfessorId;

    @Builder
    public CreateStudentServiceDto(Long departmentId, String name, String email, String score, Integer academicYear, Long advisorProfessorId)
    {
        this.departmentId = departmentId;
        this.name = name;
        this.email = email;
        this.score = score;
        this.academicYear = academicYear;
        this.advisorProfessorId = advisorProfessorId;
    }
}
