package com.sugang.toys.command.professor.application;

import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Accessors(chain = true)
public class ProfessorOpenCourseRequest {
    private Long professorId;
    private String courseName;
    private Long departmentId;
    private Set<ProfessorCourseScheduleDto> courseScheduleSet;
    private Integer maxCourseStudentCount;

}
