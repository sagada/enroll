package com.sugang.app.global.config.kafka;

import lombok.Data;

@Data
public class EnrollmentLog {
    private Long courseId;
    private Long studentId;
    private Long enrollmentId;
    private String courseName;
    private String studentName;
    private Long professorId;
    private int score;
    private String professorName;

}
