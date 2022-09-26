package com.sugang.toys.command.department.domain;

import javax.persistence.*;

@Entity
public class Department{

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Division division;

    @Embedded
    private DepartmentPhoneNumber phoneNumber;


}
