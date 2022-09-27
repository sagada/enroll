package com.sugang.toys.command.department.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;


@NoArgsConstructor
@Entity
@Getter
public class Departments {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Division division;

    @Embedded
    private DepartmentPhoneNumber phoneNumber;

    public Departments(Long id, String name, Division division, DepartmentPhoneNumber phoneNumber)
    {
        this.id = id;
        this.name = name;
        this.division = division;
        this.phoneNumber = phoneNumber;
    }

    public static Departments create(
            Long id
            , String name
            , Division division
            , DepartmentPhoneNumber phoneNumber
    )
    {
        return new Departments(id, name, division, phoneNumber);
    }
}
