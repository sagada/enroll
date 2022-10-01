package com.sugang.toys.command.classregistration.domain;

import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.student.domain.Student;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

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

    private boolean finish;

    private LocalDateTime registerDate;

    private Integer courseValue;

    public CourseRegistration(Course course, Student student, Integer courseValue)
    {
        this.course = course;
        this.student = student;
        this.courseValue = courseValue;
    }

    public static CourseRegistration register(
            Course course,
            Student student,
            Integer courseValue
    )
    {
        return new CourseRegistration(course, student, courseValue);
    }

}
