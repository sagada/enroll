package com.sugang.toys.command.student.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    private String name;

    public Student(String name)
    {
        if (name.isBlank())
        {
            throw new RuntimeException("학생 이름 누락");
        }
        this.name = name;
    }

    public static Student create(String name)
    {
        return new Student(name);
    }
}
