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
public class Enrolment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private EnrolmentCourse enrolmentCourse;

    @Embedded
    private EnrolmentStudent enrolmentStudent;

    @Embedded
    private EnrolmentInfo enrolmentInfo;

    @Column(name = "register_time")
    private LocalDateTime registerTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "enroll_status")
    private EnrolmentStatus enrolmentStatus;

    public void enroll(Long courseId, Long studentId)
    {
        Enrolment enrolment = new Enrolment(

        );
    }

    public Enrolment(
            Long courseId
            , Long studentId
            )
    {
        this.enrolmentCourse = enrolmentCourse;
        this.enrolmentStudent = enrolmentStudent;
        this.enrolmentInfo = enrolmentInfo;
        this.registerTime = registerTime;
        this.enrolmentStatus = enrolmentStatus;
    }
}
