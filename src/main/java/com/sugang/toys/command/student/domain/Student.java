package com.sugang.toys.command.student.domain;

import lombok.Getter;

import javax.persistence.*;

@Entity
@Getter
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
}
