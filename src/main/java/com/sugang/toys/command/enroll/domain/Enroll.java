package com.sugang.toys.command.enroll.domain;

import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.professor.domain.Professor;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "enroll")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
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

    private Enroll(Course course, Professor professor, Long studentId)
    {
        this.course = course;
        this.professor = professor;
        this.studentId = studentId;
    }

    public static Enroll enroll(Course course, Long studentId)
    {
        return null;
    }


}
