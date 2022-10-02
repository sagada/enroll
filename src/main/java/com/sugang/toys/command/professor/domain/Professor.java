package com.sugang.toys.command.professor.domain;

import com.sugang.toys.command.common.domain.PhoneNumber;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;

@Getter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PhoneNumber phoneNumber;

    private String name;

    public Professor(Long id, String name)
    {
        this.id = id;
        this.name = name;
    }
}
