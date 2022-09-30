package com.sugang.toys.command.course.domain;

import com.sugang.toys.command.professor.domain.Professeor;

import javax.persistence.*;

@Entity
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private CourseSchedules courseSchedules;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "professeor_id")
    private Professeor professeor;

    @Column(name = "course_value")
    private Integer courseValue;

    @Column(name = "max_student_count")
    private Integer maxStudentCount;
}
