package com.sugang.toys.command.course.domain.service;

import com.sugang.toys.command.course.domain.CourseSchedule;
import org.springframework.stereotype.Service;

@Service
public class CourseScheduleOverlapCheckService {

    public boolean isOverlap(CourseSchedule courseSchedule1, CourseSchedule courseSchedule2)
    {
        if (courseSchedule1.getEnd().isAfter(courseSchedule2.getEnd()) && courseSchedule1.getStart().isBefore(courseSchedule2.getEnd()))
        {
            return true;
        }

        return courseSchedule2.getEnd().isAfter(courseSchedule1.getEnd()) && courseSchedule2.getStart().isBefore(courseSchedule1.getEnd());
    }
}
