package com.sugang.app.domain.enroll;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;

@NoArgsConstructor
@Embeddable
@Getter
public class EnrollStudent {
    
    private Long studentId;

    @Embedded
    private ExaminationScore examinationScore;

    public ExaminationScore getExaminationScore()
    {
        return examinationScore;
    }

    public EnrollStudent(Long studentId)
    {
        this.studentId = studentId;
    }

    public void calculateScore(ExaminationScore examinationScore)
    {
        this.examinationScore = examinationScore;
    }
}
