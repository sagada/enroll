package com.sugang.toys.command.registercourse.domain;

import com.sugang.toys.command.course.domain.Course;

import javax.persistence.*;

@Entity
public class RegistrationCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @ManyToOne
    @JoinColumn
    private Professor professor;
}
