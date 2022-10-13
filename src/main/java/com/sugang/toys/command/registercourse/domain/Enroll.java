package com.sugang.toys.command.registercourse.domain;

import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.professor.domain.Professor;

import javax.persistence.*;

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

}
