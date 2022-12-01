package com.sugang.toys.command.course.domain;

import lombok.Getter;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
public class StudentCount {

    @Column(name = "student_max_count")
    private int studentMaxCount;

    @Column(name = "enroll_student_count")
    private int enrollStudentCount;

    public boolean overStudentCount()
    {
        return studentMaxCount <= enrollStudentCount;
    }

    public StudentCount(int studentMaxCount)
    {
        if (studentMaxCount == 0)
        {
            throw new IllegalStateException("student Count zero");
        }

        this.studentMaxCount = studentMaxCount;
        this.enrollStudentCount = 0;
    }

    public StudentCount(int studentMaxCount, int enrollStudentCount)
    {
        this.studentMaxCount = studentMaxCount;
        this.enrollStudentCount = enrollStudentCount;
    }
}
