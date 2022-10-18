package com.sugang.toys.command.enroll.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EnrollInfo {

    @Column(name = "score")
    private Integer score;
}
