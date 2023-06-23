package com.sugang.app.global.common.event;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class EnrollRegisteredEvent {
    private final Long courseId;
    private final Long studentId;
    private final Long professorId;
    private final Long enrollmentId;
    private final String courseName;
    private final String studentName;
    private final int courseScore;

    @Builder
    public EnrollRegisteredEvent(
            Long courseId,
            Long studentId,
            Long professorId,
            Long enrollmentId,
            String courseName,
            String studentName,
            int courseScore)
    {
        this.courseId = courseId;
        this.studentId = studentId;
        this.professorId = professorId;
        this.enrollmentId = enrollmentId;
        this.courseName = courseName;
        this.studentName = studentName;
        this.courseScore = courseScore;
    }
}
