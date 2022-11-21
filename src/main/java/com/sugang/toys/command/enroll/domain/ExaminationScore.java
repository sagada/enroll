package com.sugang.toys.command.enroll.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Embeddable
public class ExaminationScore {

    private Double midTermTotalScore;
    private Double finalTotalScore;
    private Double midtermScore;
    private Double finalScore;

}
