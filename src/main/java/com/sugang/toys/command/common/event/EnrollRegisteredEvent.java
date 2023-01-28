package com.sugang.toys.command.common.event;

import lombok.Getter;

@Getter
public class EnrollRegisteredEvent {

    private final Long studentId;
    private final Long courseId;
    private final Long enrollId;

    public EnrollRegisteredEvent(Long studentId, Long courseId, Long enrollId)
    {
        this.studentId = studentId;
        this.courseId = courseId;
        this.enrollId = enrollId;
    }
}
