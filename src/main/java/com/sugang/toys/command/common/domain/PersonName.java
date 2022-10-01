package com.sugang.toys.command.common.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Embeddable
public class PersonName {
    private String name;

    public PersonName(String name)
    {
        this.name = name;
    }
}
