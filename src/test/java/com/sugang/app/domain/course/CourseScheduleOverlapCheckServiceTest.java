package com.sugang.app.domain.course;

import com.google.common.collect.Sets;
import com.sugang.app.TestContainerIntegrationTestSupport;
import com.sugang.app.domain.course.service.CourseScheduleOverlapCheckService;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;

public class CourseScheduleOverlapCheckServiceTest extends TestContainerIntegrationTestSupport {

    @Autowired
    CourseScheduleOverlapCheckService courseScheduleOverlapCheckService;

    @Test
    @DisplayName("수업 스케줄이 겹치지 않는 것을 확인할 수 있다.")
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
    @DisplayName("수업 스케줄이 겹치는 것을 확인할 수 있다.")
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
