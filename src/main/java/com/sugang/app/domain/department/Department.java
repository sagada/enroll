package com.sugang.app.domain.department;

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

    private String number;

    private Department(Long id, String name, Division division,  String number)
    {
        this.id = id;
        this.name = name;
        this.division = division;
        this.number = number;
    }

    public static Department create(
            String name
            , Division division
            , String number
    )
    {
        return new Department(null, name, division, number);
    }
}
