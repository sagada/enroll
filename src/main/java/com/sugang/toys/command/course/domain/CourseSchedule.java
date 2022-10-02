package com.sugang.toys.command.course.domain;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.DayOfWeek;
import java.time.LocalDateTime;


@Setter
@Getter
@Embeddable
@NoArgsConstructor
@EqualsAndHashCode(of = {"day", "start", "end", "roomNumber"})
public class CourseSchedule {

    private DayOfWeek day;
    private LocalDateTime start;
    private LocalDateTime end;

    @Embedded
    @Column(name = "room_number")
    private RoomNumber roomNumber;

    public CourseSchedule(
            DayOfWeek day
            , LocalDateTime start
            , LocalDateTime end
            , String roomNumber)
    {
        this.day = day;
        this.start = start;
        this.end = end;
        this.roomNumber = new RoomNumber(roomNumber);
    }
}
