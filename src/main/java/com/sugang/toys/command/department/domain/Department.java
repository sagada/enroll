package com.sugang.toys.command.department.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@Entity
public class Department{

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    private String name;

    @Enumerated(EnumType.STRING)
    private Division division;

    @Embedded
    private DepartmentPhoneNumber phoneNumber;

    public Department(Long id, String name, Division division, DepartmentPhoneNumber phoneNumber)
    {
        this.id = id;
        this.name = name;
        this.division = division;
        this.phoneNumber = phoneNumber;
    }

    public static Department create(
            Long id
            , String name
            , Division division
            , DepartmentPhoneNumber phoneNumber
    )
    {
        return new Department(id, name, division, phoneNumber);
    }
}
