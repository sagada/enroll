package com.sugang.toys.command.enroll.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@Embeddable
public class EnrolmentInfo {

    @Column(name = "score")
    private Integer score;
}
