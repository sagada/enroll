package com.sugang.toys.command.student.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    private String name;

    public static Student create(String name)
    {
        Student student = new Student();
        student.name = name;

        return student;
    }
}
