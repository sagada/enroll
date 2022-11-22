package com.sugang.toys.command.enroll.domain;

import com.sugang.toys.command.enroll.application.EnrollmentCreateValidate;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "enrollment")
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

    @Column(name = "course_id")
    private Long courseId;

    @Embedded
    private EnrollStudent enrollStudent;

    @Enumerated(EnumType.STRING)

    @Column(name = "enroll_status")
    private EnrolmentStatus enrolmentStatus;

    public Enrollment(
            Long courseId
            , Long studentId
            , EnrolmentStatus enrolmentStatus)
    {
        this.courseId = courseId;
        this.enrollStudent = new EnrollStudent(studentId);
        this.enrolmentStatus = enrolmentStatus;
    }

    public static Enrollment enroll(
            Long courseId
            , Long studentId
            , EnrollmentCreateValidate enrollmentCreateValidate)
    {
        enrollmentCreateValidate.validate(courseId, studentId);

        return new Enrollment(
                courseId
                , studentId
                , EnrolmentStatus.PROPOSE
        );
    }

    public void calculateScore(ExaminationScore examinationScore)
    {
        verifyEndEnrollment();
        enrollStudent.calculateScore(examinationScore);
    }

    private void verifyEndEnrollment()
    {
        if(enrolmentStatus.equals(EnrolmentStatus.END))
        {
            throw new IllegalStateException("종료된 강의");
        }
    }
}
