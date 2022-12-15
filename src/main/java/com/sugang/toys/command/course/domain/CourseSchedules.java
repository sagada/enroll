package com.sugang.toys.command.course.domain;

import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.Set;

@Setter
@NoArgsConstructor
@Embeddable
@Getter
public class CourseSchedules {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "course_scheduler", joinColumns = @JoinColumn(name = "course_id"))
    private Set<CourseSchedule> courseScheduleSet;

    public CourseSchedules(Set<CourseSchedule> courseScheduleSet)
    {
        if (CollectionUtils.isEmpty(courseScheduleSet))
        {
            throw new RuntimeException("스케줄은 1개 이상 있어야됩니다.");
        }

        this.courseScheduleSet = courseScheduleSet;
    }

}