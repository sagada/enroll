package com.sugang.toys.command.enroll.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CourseEnroll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
