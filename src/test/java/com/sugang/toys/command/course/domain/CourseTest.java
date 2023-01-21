package com.sugang.toys.command.course.domain;

import com.sugang.toys.command.course.domain.exception.CourseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CourseTest {

    static Course givenCourse()
    {
        return new Course(
                CourseTestHelper.SCHEDULES,
                CourseTestHelper.EXAMINATION,
                CourseTestHelper.SUMMARIES,
                Set.of(1L, 2L),
                1L,
                1L,
                new CourseName("courseName"),
                CourseStatus.OPEN,
                2
        );
    }

    @Test
    void createTest()
    {
        Course course = givenCourse();

        assertThat(course.getCourseExamination().getMidTermDate())
                .isEqualTo(LocalDateTime.of(2022, Month.JANUARY, 13, 13, 15));
        assertThat(course.getCourseStatus()).isEqualTo(CourseStatus.OPEN);
        assertThat(course.getCourseName()).isEqualTo(new CourseName("courseName"));
        assertThat(course.getCourseSummaries()).extracting(CourseSummaries::getCourseSummaries).isEqualTo(CourseTestHelper.SUMMARIES);
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
        Course course = givenCourse();

        Course updateCourse = new Course(
                CourseTestHelper.SCHEDULES,
                CourseTestHelper.UPDATED_EXAMINATION,
                CourseTestHelper.UPDATED_SUMMARIES,
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
        assertThat(course.getCourseExamination()).isEqualTo(CourseTestHelper.UPDATED_EXAMINATION);
        assertThat(course.getCourseSummaries().getCourseSummaries()).isEqualTo(CourseTestHelper.UPDATED_SUMMARIES);
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