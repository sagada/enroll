package com.sugang.toys.command.enroll.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EnrolmentCourse {

    @Column(name = "course_id")
    private Long courseId;

    @Column(name = "professor_id")
    private Long courseProfessorId;

    @Column(name = "course_name")
    private String courseName;
}
