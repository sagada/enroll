package com.sugang.toys.command.course.domain;

import com.google.common.collect.Sets;
import com.sugang.toys.command.course.domain.exception.CourseException;
import com.sugang.toys.command.course.domain.validator.CreateCourseValidator;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

import static org.assertj.core.api.Assertions.*;

class CourseTest {

    @Test
    void courseCreateTest()
    {
        Set<CourseSchedule> courseSchedules = Sets.newHashSet(
                new CourseSchedule(
                        LocalDateTime.of(2022, Month.MARCH, 1, 13, 30),
                        LocalDateTime.of(2022, Month.MARCH, 1, 14, 30),
                        "2004"
                )
        );

        Set<CourseSummary> courseSummaries = Sets.newHashSet(
                new CourseSummary(1, "content", "title")
        );

        CourseExamination courseExamination = new CourseExamination(
                LocalDateTime.of(2022, Month.JANUARY, 13, 13, 15),
                LocalDateTime.of(2022, Month.SEPTEMBER, 13, 14, 15)
        );

        Course course = Course.createCourse(
                courseSchedules,
                courseSummaries,
                courseExamination,
                Set.of(1L, 2L),
                1L,
                1L,
                new CourseName("courseName"),
                 2,
                 Mockito.mock(CreateCourseValidator.class)
                );

        assertThat(course.getCourseExamination().getMidTermDate())
                .isEqualTo(LocalDateTime.of(2022, Month.JANUARY, 13, 13, 15));
        assertThat(course.getCourseName()).isEqualTo(new CourseName("courseName"));
        assertThat(course.getCourseSummaries()).extracting(CourseSummaries::getCourseSummaries).isEqualTo(courseSummaries);
        assertThat(course.getPrerequisiteCourse()).extracting(PrerequisiteCourse::getPreCourseSeqList).isEqualTo(Set.of(1L, 2L));
        assertThat(course.getCourseSchedules().getCourseScheduleSet()).hasSize(1);
    }

    @Test
    void courseExaminationErrorTest()
    {
        assertThatThrownBy(() -> new CourseExamination(
                LocalDateTime.of(2022, Month.SEPTEMBER, 13, 14, 15),
                LocalDateTime.of(2022, Month.JANUARY, 13, 13, 15)
        )).isInstanceOf(CourseException.class).hasMessage("examination date error");
    }
}