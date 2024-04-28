package com.sugang.app.domain.enroll;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Embeddable
@Getter
class ExaminationScore {

    private Double midTermTotalScore;
    private Double finalTotalScore;
    private Double midtermScore;
    private Double finalScore;
    private String midExtraCredit;
    private String finalExtraCredit;
}
