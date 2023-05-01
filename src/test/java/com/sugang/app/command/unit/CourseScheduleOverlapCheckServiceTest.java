package com.sugang.app.command.unit;

import com.google.common.collect.Sets;
import com.sugang.app.domain.course.domain.CourseSchedule;
import com.sugang.app.domain.course.domain.service.CourseScheduleOverlapCheckService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;

import java.time.LocalDateTime;

public class CourseScheduleOverlapCheckServiceTest {

    CourseScheduleOverlapCheckService courseScheduleOverlapCheckService = new CourseScheduleOverlapCheckService();

    @Test
    @DisplayName("시간이 겹치면 true return")
    public void whenCourseScheduleOverlapThenReturnTrue()
    {
        // given
        CourseSchedule c1 = new CourseSchedule(
                LocalDateTime.of(2023,1, 13, 13, 30)
                ,LocalDateTime.of(2023,1, 13, 14, 50)
                , "1004");

        CourseSchedule c2 = new CourseSchedule(
                LocalDateTime.of(2023,1, 13, 13, 0)
                ,LocalDateTime.of(2023,1, 13, 14, 50)
                , "1004");

        boolean overlap = courseScheduleOverlapCheckService.isOverlap(Sets.newHashSet(c1), Sets.newHashSet(c2));

        Assertions.assertThat(overlap).isTrue();
    }

    @Test
    @DisplayName("시간이 겹치지 않으면 false return")
    public void whenCourseScheduleNotOverlapThenReturnFalse()
    {
        // given
        CourseSchedule c1 = new CourseSchedule(
                LocalDateTime.of(2023,1, 13, 13, 30)
                ,LocalDateTime.of(2023,1, 13, 14, 50)
                , "1004");

        CourseSchedule c2 = new CourseSchedule(
                LocalDateTime.of(2023,1, 13, 15, 0)
                ,LocalDateTime.of(2023,1, 13, 16, 50)
                , "1004");

        boolean overlap = courseScheduleOverlapCheckService.isOverlap(Sets.newHashSet(c1), Sets.newHashSet(c2));

        Assertions.assertThat(overlap).isFalse();
    }
}
