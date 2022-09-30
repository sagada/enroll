package com.sugang.toys.command.professor.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Professeor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
}
