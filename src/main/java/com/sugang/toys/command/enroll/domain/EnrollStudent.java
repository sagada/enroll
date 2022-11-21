package com.sugang.toys.command.enroll.domain;

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
    private ExaminationScore examinationInfo;

    public EnrollStudent(Long studentId) {
        this.studentId = studentId;
    }
}
