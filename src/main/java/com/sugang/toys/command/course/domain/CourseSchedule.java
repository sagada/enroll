package com.sugang.toys.command.course.domain;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.DayOfWeek;

@Setter
@Getter
@Embeddable
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = {"day", "from", "to", "roomNumber"})
public class CourseSchedule {

    private DayOfWeek day;
    private String from;
    private String to;

    @Embedded
    private RoomNumber roomNumber;

    public CourseSchedule(DayOfWeek day, String from, String to, String roomNumber)
    {
        this.day = day;
        this.from = from;
        this.to = to;
        this.roomNumber = new RoomNumber(roomNumber);
    }
}
