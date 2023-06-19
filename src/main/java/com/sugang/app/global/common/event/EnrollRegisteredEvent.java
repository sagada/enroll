package com.sugang.app.global.common.event;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EnrollRegisteredEvent {
    private final Long studentId;
    private final Long courseId;
    private final Long enrollId;

    @Builder
    public EnrollRegisteredEvent(Long studentId, Long courseId, Long enrollId) {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollId = enrollId;
    }
}
