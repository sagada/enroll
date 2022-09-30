package com.sugang.toys.command.classregistration.domain;

import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.student.domain.Student;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
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

    public CourseRegistration(Course course, Student student)
    {
        this.course = course;
        this.student = student;
    }

}
