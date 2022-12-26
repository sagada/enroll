package com.sugang.toys.command.course.domain.service;

import com.sugang.toys.command.course.domain.CourseSchedule;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Set;

@Service
public class CourseScheduleOverlapCheckService {

    public boolean isOverlap(CourseSchedule c1, CourseSchedule c2)
    {
        return true;
    }

    public void isOverlap(Set<CourseSchedule> courseScheduleSet1, Set<CourseSchedule> courseScheduleSet2)
    {
        if (CollectionUtils.isEmpty(courseScheduleSet1) || CollectionUtils.isEmpty(courseScheduleSet2))
        {
            return ;
        }
        // TODO 구현
    }

}
