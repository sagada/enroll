package com.sugang.toys.command.course.domain.service;

import com.sugang.toys.command.course.domain.CourseSchedule;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class CourseScheduleOverlapCheckService {

    public boolean isOverlap(Set<CourseSchedule> c1, Set<CourseSchedule> c2)
    {
        List<CourseSchedule> c1List = sortCourseSchedules(c1);
        List<CourseSchedule> c2List = sortCourseSchedules(c2);
        return isOverlap(c1List, c2List);
    }

    private boolean isOverlap(List<CourseSchedule> c1List, List<CourseSchedule> c2List)
    {
        for (CourseSchedule courseSchedule1 : c1List)
        {
            for (CourseSchedule courseSchedule2 : c2List)
            {
                if (courseSchedule1.getEnd().isAfter(courseSchedule2.getStart())
                    && courseSchedule2.getStart().isAfter(courseSchedule1.getStart()))
                    return true;

                if (courseSchedule2.getEnd().isAfter(courseSchedule1.getStart())
                        && courseSchedule1.getStart().isAfter(courseSchedule2.getStart()))
                    return true;
            }
        }

        return false;
    }

    private static List<CourseSchedule> sortCourseSchedules(Set<CourseSchedule> list)
    {
        return list.stream()
                .sorted(Comparator.comparing(CourseSchedule::getStart))
                .collect(Collectors.toList());
    }
}
