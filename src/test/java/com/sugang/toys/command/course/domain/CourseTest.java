package com.sugang.toys.command.course.domain;

import com.sugang.toys.command.course.domain.validator.CreateCourseValidator;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

class CourseTest {

    @Test
    void courseCreateTest()
    {
        Set<CourseSchedule> courseSchedules = Sets.newHashSet();
        Set<CourseSummary> courseSummaries = Sets.newHashSet();
        Course course = Course.createCourse(
                courseSchedules,
                courseSummaries,
                new CourseExamination(
                        LocalDateTime.of(2022, Month.OCTOBER, 13, 13, 15),
                        LocalDateTime.of(2022, Month.SEPTEMBER, 13, 14, 15)
                ),
                Set.of(1L, 2L),
                1L,
                1L,
                new CourseName("courseName"),
                 10,
                 Mockito.mock(CreateCourseValidator.class)
                );

        Assertions.assertThat(course.getCourseExamination().getMidTermDate()).isEqualTo(
                LocalDateTime.of(2022, Month.OCTOBER, 13, 13, 15)
        );
    }
}