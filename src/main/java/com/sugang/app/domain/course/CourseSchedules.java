package com.sugang.app.domain.course;

import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
import java.util.Set;

@Getter
@NoArgsConstructor
@Embeddable
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