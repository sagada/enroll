package com.sugang.toys.command.department.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "department")
@NoArgsConstructor
@Entity
@Getter
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private Division division;

    @Embedded
    private DepartmentPhoneNumber phoneNumber;


    public Department(Long id, String name, Division division, String phoneNumber, String faxNumber)
    {
        this.id = id;
        this.name = name;
        this.division = division;
        this.phoneNumber = new DepartmentPhoneNumber(phoneNumber, faxNumber);
    }

    public static Department create(
            Long id
            , String name
            , Division division
            , String phoneNumber
            , String faxNumber
    )
    {
        return new Department(id, name, division, phoneNumber, faxNumber);
    }
}
