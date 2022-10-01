package com.sugang.toys.command.professor.domain;

import com.sugang.toys.command.common.domain.PhoneNumber;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PhoneNumber phoneNumber;

    private String name;
}
