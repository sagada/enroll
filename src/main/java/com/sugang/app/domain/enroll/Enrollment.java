package com.sugang.app.domain.enroll;

import com.sugang.app.domain.BaseEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "enrollment")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Getter
public class Enrollment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long courseId;
    private Long studentId;

    private boolean isCompleted;

    @Embedded
    private ExaminationScore examinationScore;

    @Enumerated(EnumType.STRING)
    private EnrolmentStatus enrolmentStatus;

    public Enrollment(
            Long courseId
            , Long studentId
            , EnrolmentStatus enrolmentStatus)
    {
        this.courseId = courseId;
        this.studentId = studentId;
        this.enrolmentStatus = enrolmentStatus;
        this.isCompleted = false;
    }

    public static Enrollment enroll(
            Long courseId
            , Long studentId)
    {
        return new Enrollment(
                courseId
                , studentId
                , EnrolmentStatus.PROPOSE
        );
    }
}
