package com.sugang.app.domain.enroll.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Embeddable
@Getter
public class ExaminationScore {

    private Double midTermTotalScore;
    private Double finalTotalScore;
    private Double midtermScore;
    private Double finalScore;
    private String midExtraCredit;
    private String finalExtraCredit;
}
