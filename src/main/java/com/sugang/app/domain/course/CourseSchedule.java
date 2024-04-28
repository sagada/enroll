package com.sugang.app.domain.course;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.Embedded;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;


@Setter
@Getter
@Embeddable
@NoArgsConstructor
@EqualsAndHashCode(of = {"start", "end", "roomNumber"})
public class CourseSchedule {

    @Column(name = "start")
    private LocalDateTime start;

    @Column(name = "end")
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
