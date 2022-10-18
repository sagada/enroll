package com.sugang.toys.command.enroll.domain;

import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.professor.domain.Professor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Table(name = "enroll")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@SecondaryTable(
        name = "course_credit",
        pkJoinColumns = @PrimaryKeyJoinColumn(name = "enroll_id")
)
@Entity
public class Enroll {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @Column(name = "student_id")
    private Long studentId;

    @Column(name = "reg_dt")
    private LocalDateTime registerTime;

    @Enumerated(EnumType.STRING)
    @Column(name = "enroll_status")
    private EnrollStatus enrollStatus;

    @Embedded
    private CourseCredit courseCredit;

    @Embedded
    private EnrollInfo enrollInfo;

    private Enroll(Course course, Professor professor, Long studentId)
    {
        this.course = course;
        this.professor = professor;
        this.studentId = studentId;
    }
}
