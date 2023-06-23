package com.sugang.app.api.service.course;

import com.google.common.collect.Sets;
import com.sugang.app.domain.course.CourseSchedule;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;


class CourseScheduleOverlapCheckServiceTest {

    CourseScheduleOverlapCheckService courseScheduleOverlapCheckService = new CourseScheduleOverlapCheckService();

    @Test
    @DisplayName("수업 스케줄이 겹치는 것을 확인할 수 있다.")
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

        // when
        boolean overlap = courseScheduleOverlapCheckService.isOverlap(Sets.newHashSet(c1), Sets.newHashSet(c2));

        // then
        Assertions.assertThat(overlap).isTrue();
    }

    @Test
    @DisplayName("수업 스케줄이 겹치지 않는 것을 확인할 수 있다.")
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

        // when
        boolean overlap = courseScheduleOverlapCheckService.isOverlap(Sets.newHashSet(c1), Sets.newHashSet(c2));

        // then
        Assertions.assertThat(overlap).isFalse();
    }
}