package com.sugang.app.global.common.event;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EnrollRegisteredEvent {
    private final Long studentId;
    private final Long courseId;

    @Builder
    public EnrollRegisteredEvent(Long studentId, Long courseId) {
        this.studentId = studentId;
        this.courseId = courseId;
    }
}
