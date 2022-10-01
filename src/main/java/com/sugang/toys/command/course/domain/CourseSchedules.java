package com.sugang.toys.command.course.domain;

import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@EqualsAndHashCode(of = {"courseScheduleList"})
@Setter
@NoArgsConstructor
@Embeddable
@Getter
@ToString
public class CourseSchedules {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "course_scheduler", joinColumns = @JoinColumn(name = "course_id"))
    private List<CourseSchedule> courseScheduleList;

    public int getCourseScheduleSize()
    {
        return courseScheduleList.size();
    }

    public CourseSchedules(List<CourseSchedule> courseScheduleList)
    {
        if (CollectionUtils.isEmpty(courseScheduleList))
        {
            throw new RuntimeException("스케줄은 1개 이상 있어야됩니다.");
        }

        Set<CourseSchedule> courseScheduleSet = new HashSet<>(courseScheduleList);

        if (courseScheduleList.size() != courseScheduleSet.size())
        {
            throw new RuntimeException("스케줄이 중복됩니다.");
        }

        this.courseScheduleList = courseScheduleList;
    }

}