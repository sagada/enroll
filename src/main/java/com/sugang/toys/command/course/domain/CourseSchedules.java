package com.sugang.toys.command.course.domain;

import lombok.*;
import org.springframework.util.CollectionUtils;

import javax.persistence.*;
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
    private Set<CourseSchedule> courseScheduleList;

    public CourseSchedules(Set<CourseSchedule> courseScheduleList)
    {
        if (CollectionUtils.isEmpty(courseScheduleList))
        {
            throw new RuntimeException("스케줄은 1개 이상 있어야됩니다.");
        }

        this.courseScheduleList = courseScheduleList;
    }
}