package com.sugang.toys.command.enroll.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@Embeddable
public class EnrolmentStudent {

    @Column(name = "student_id")
    private Long studentId;

    @Embedded
    private CourseCredit courseCredit;
}
