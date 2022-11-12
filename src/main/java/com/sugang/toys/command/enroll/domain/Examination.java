package com.sugang.toys.command.enroll.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDateTime;

@NoArgsConstructor
@Embeddable
public class Examination {
    private LocalDateTime midTermExamDate;
    private LocalDateTime finalExamDate;
}
