package com.sugang.toys.command.enroll.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "enrolment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SecondaryTable(
        name = "course_credit",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "enroll_id")
)
@Entity
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private EnrolmentCourse enrolmentCourse;

    @Embedded
    private EnrolmentStudent enrolmentStudent;

    @Embedded
    private Examination examination;

    @Embedded
    private EnrolmentInfo enrolmentInfo;

    @Column(name = "register_time")
    private LocalDateTime registerTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "enroll_status")
    private EnrolmentStatus enrolmentStatus;

    public Enrollment(
            Long courseId
            , Long studentId
            , Integer score
            , LocalDateTime midTermExamDate
            , LocalDateTime finalExamDate
            )
    {
        this.enrolmentCourse = enrolmentCourse;
        this.enrolmentStudent = enrolmentStudent;
        this.enrolmentInfo = enrolmentInfo;
        this.registerTime = registerTime;
        this.enrolmentStatus = enrolmentStatus;
    }
}
