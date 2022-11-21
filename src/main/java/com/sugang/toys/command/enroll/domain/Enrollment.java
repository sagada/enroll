package com.sugang.toys.command.enroll.domain;

import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.enroll.application.EnrollmentCreateValidate;
import com.sugang.toys.command.student.domain.Student;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "enrolment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SecondaryTable(
        name = "course_credit",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "enroll_id")
)
@Entity
@Getter
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id")
    private Student student;

    private int score;

    @Embedded
    private EnrollStudent enrollStudent;

    @Enumerated(EnumType.STRING)
    @Column(name = "enroll_status")
    private EnrolmentStatus enrolmentStatus;

    public Enrollment(
            Course course
            , Student student
            , Integer score
            , EnrolmentStatus enrolmentStatus
            )
    {
        this.score = score;
        this.course = course;
        this.student = student;
        this.enrolmentStatus = enrolmentStatus;
    }

    public static Enrollment enroll(
            Course course
            , Student student
            , EnrollmentCreateValidate enrollmentCreateValidate)
    {
        enrollmentCreateValidate.validate(course, student);

        return new Enrollment(
                course
                , student
                , course.getScore()
                , EnrolmentStatus.PROPOSE
        );
    }
}
