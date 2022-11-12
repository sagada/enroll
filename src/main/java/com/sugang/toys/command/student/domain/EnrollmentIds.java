package com.sugang.toys.command.student.domain;

import lombok.Getter;

import javax.persistence.Embeddable;
import java.util.Set;

@Embeddable
@Getter
public class EnrollmentIds {
    private Set<Long> enrollementIDs;
}
