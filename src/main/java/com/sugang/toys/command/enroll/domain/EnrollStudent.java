package com.sugang.toys.command.enroll.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@NoArgsConstructor
@Embeddable
public class EnrollStudent {
    
    private Long studentId;
    
    private ExaminationScore examinationInfo;
    
    
}
