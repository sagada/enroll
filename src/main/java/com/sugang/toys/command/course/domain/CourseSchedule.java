package com.sugang.toys.command.course.domain;

import lombok.*;

import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.DayOfWeek;
import java.time.LocalDateTime;

@Setter
@Getter
@Embeddable
@ToString
@NoArgsConstructor
@EqualsAndHashCode(of = {"day", "from", "to", "roomNumber"})
public class CourseSchedule {

    private DayOfWeek day;
    private LocalDateTime from;
    private LocalDateTime to;

    @Embedded
    private RoomNumber roomNumber;

    public CourseSchedule(DayOfWeek day, LocalDateTime from, LocalDateTime to, String roomNumber)
    {
        this.day = day;
        this.from = from;
        this.to = to;
        this.roomNumber = new RoomNumber(roomNumber);
    }
}
