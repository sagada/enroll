package com.sugang.app.domain.enroll;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EnrolmentInfo {

    @Column(name = "score")
    private Integer score;
}