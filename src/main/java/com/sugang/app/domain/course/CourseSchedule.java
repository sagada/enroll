package com.sugang.app.domain.course;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.LocalDateTime;


@Setter
@Getter
@Embeddable
@NoArgsConstructor
@EqualsAndHashCode(of = {"start", "end", "roomNumber"})
public class CourseSchedule {

    private LocalDateTime start;
    private LocalDateTime end;

    @Embedded
    @Column(name = "room_number")
    private RoomNumber roomNumber;

    public CourseSchedule(
            LocalDateTime start
            , LocalDateTime end
            , String roomNumber)
    {
        this.start = start;
        this.end = end;
        this.roomNumber = new RoomNumber(roomNumber);
    }
}
