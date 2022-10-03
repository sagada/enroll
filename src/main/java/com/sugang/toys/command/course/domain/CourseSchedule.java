package com.sugang.toys.command.course.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Embedded;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Set;


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

    public boolean contain(Set<CourseSchedule> addCourseSchedules)
    {
        for (CourseSchedule courseSchedule : addCourseSchedules)
        {
            if (courseSchedule.getEnd().isAfter(this.getEnd()) && courseSchedule.getStart().isBefore(this.getEnd()))
            {
                return true;
            }

            if (this.getEnd().isAfter(courseSchedule.getEnd()) && this.getStart().isBefore(this.getEnd()))
            {
                return true;
            }
        }

        return false;
    }
}
