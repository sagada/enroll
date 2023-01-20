package com.sugang.toys.command.course.domain;

import com.google.common.collect.Sets;
import com.sugang.toys.command.course.domain.exception.CourseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CourseTest {

    static Set<CourseSchedule> givenCourseSchedules()
    {
        return Sets.newHashSet(
                new CourseSchedule(
                        LocalDateTime.of(2022, Month.MARCH, 1, 13, 30),
                        LocalDateTime.of(2022, Month.MARCH, 1, 14, 30),
                        "2004"
                )
        );
    }

    static Set<CourseSummary> givenCourseSummaries()
    {
        return Sets.newHashSet(
                new CourseSummary(1, "content", "title")
        );
    }

    static CourseExamination givenCourseExamination()
    {
        return new CourseExamination(
                LocalDateTime.of(2022, Month.JANUARY, 13, 13, 15),
                LocalDateTime.of(2022, Month.SEPTEMBER, 13, 14, 15)
        );
    }

    static Course givenCourse()
    {
        Set<CourseSchedule> courseSchedules = givenCourseSchedules();
        Set<CourseSummary> courseSummaries = givenCourseSummaries();
        CourseExamination courseExamination = givenCourseExamination();

        return new Course(
            courseSchedules,
            courseExamination,
            courseSummaries,
            Set.of(1L, 2L),
            1L,
            1L,
            new CourseName("courseName"),
            CourseStatus.OPEN,
            2
    );

    }

    @Test
    void courseCreateTest()
    {
        Course course = givenCourse();

        assertThat(course.getCourseExamination().getMidTermDate())
                .isEqualTo(LocalDateTime.of(2022, Month.JANUARY, 13, 13, 15));
        assertThat(course.getCourseStatus()).isEqualTo(CourseStatus.OPEN);
        assertThat(course.getCourseName()).isEqualTo(new CourseName("courseName"));
        assertThat(course.getCourseSummaries()).extracting(CourseSummaries::getCourseSummaries).isEqualTo(givenCourseSummaries());
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

    @Test
    void updateTest()
    {
        // given
        Set<CourseSchedule> courseSchedules = givenCourseSchedules();
        Course course = givenCourse();

        CourseExamination updateCourseExamination = new CourseExamination(
                LocalDateTime.of(2022, Month.JANUARY, 13, 13, 15),
                LocalDateTime.of(2022, Month.SEPTEMBER, 13, 15, 15)
        );
        Set<CourseSummary> updatedSummaries = Sets.newHashSet(
                new CourseSummary(1, "updatedContent1", "updatedTitle1"),
                new CourseSummary(2, "updatedContent2", "updatedTitle2"),
                new CourseSummary(3, "updatedContent3", "updatedTitle3")
        );

        Course updateCourse = new Course(
                courseSchedules,
                updateCourseExamination,
                updatedSummaries,
                Set.of(1L, 2L),
                1L,
                1L,
                new CourseName("courseName"),
                CourseStatus.OPEN,
                2
        );

        // when
        course.update(updateCourse);

        // then
        assertThat(course.getCourseExamination()).isEqualTo(updateCourseExamination);
        assertThat(course.getCourseSummaries().getCourseSummaries()).isEqualTo(updatedSummaries);
    }

    @Test
    void closeTest()
    {
        // given
        Course course = givenCourse();

        // when
        course.close();

        // then
        assertThat(course.getCourseStatus()).isEqualTo(CourseStatus.CLOSE);
    }

    @Test
    @DisplayName("이미 OPEN 수강 OPEN 시 에러")
    void alreadyOpenErrorTest()
    {
        // given
        Course course = givenCourse();

        // when
        assertThatThrownBy(course::open)
                // then
                .isInstanceOf(CourseException.class)
                .hasMessage("already open");
    }
}