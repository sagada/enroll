package com.sugang.toys.command.classregistration.domain;

import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.student.domain.Student;

import javax.persistence.*;

@Entity
public class CourseRegistration {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @JoinColumn(name = "course_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Course course;

    @JoinColumn(name = "student_id")
    @ManyToOne(fetch = FetchType.LAZY)
    private Student student;

}
