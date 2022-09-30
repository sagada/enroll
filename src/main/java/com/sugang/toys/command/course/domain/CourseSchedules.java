package com.sugang.toys.command.course.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.List;

@NoArgsConstructor
@Embeddable
public class CourseSchedules {

    private List<CourseSchedule> courseScheduleList;
    private RoomNumber roomNumber;
}