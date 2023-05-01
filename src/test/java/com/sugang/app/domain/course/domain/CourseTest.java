package com.sugang.app.domain.course.domain;

import com.sugang.app.domain.course.domain.exception.CourseException;
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
                CourseTestHelper.PRE_COURSE_ID_SET,
                CourseTestHelper.COURSE_NAME,
                CourseStatus.OPEN,
                2,
                1L,
                1L
        );
    }

    @Test
    @DisplayName("Course 생성 테스트")
    void createTest()
    {
        Course course = givenCourse();

        assertThat(course.getCourseExamination().getMidTermDate())
                .isEqualTo(LocalDateTime.of(2022, Month.JANUARY, 13, 13, 15));
        assertThat(course.getCourseStatus()).isEqualTo(CourseStatus.OPEN);
        assertThat(course.getCourseName()).isEqualTo(CourseTestHelper.COURSE_NAME);
        assertThat(course.getSyllabus()).extracting(Syllabus::getCourseSummaries).isEqualTo(CourseTestHelper.SUMMARIES);
        assertThat(course.getPrerequisiteCourse()).extracting(PrerequisiteCourse::getPreCourseSeqList).isEqualTo(Set.of(1L, 2L));
        assertThat(course.getCourseSchedules().getCourseScheduleSet()).hasSize(1);
    }

    @Test
    @DisplayName("Course examination 날짜 에러 생성 테스트")
    void courseExaminationErrorTest()
    {
        assertThatThrownBy(() -> new CourseExamination(
                LocalDateTime.of(2022, Month.SEPTEMBER, 13, 14, 15),
                LocalDateTime.of(2022, Month.JANUARY, 13, 13, 15)
        )).isInstanceOf(CourseException.class).hasMessage("examination date error");
    }

    @Test
    @DisplayName("Course 수정 테스트")
    void updateTest()
    {
        // given
        Course course = new Course(
                CourseTestHelper.SCHEDULES,
                CourseTestHelper.EXAMINATION,
                CourseTestHelper.SUMMARIES,
                CourseTestHelper.PRE_COURSE_ID_SET,
                CourseTestHelper.COURSE_NAME,
                CourseStatus.OPEN,
                2,
                1L,
                1L
        );

        Course updateCourse = new Course(
                CourseTestHelper.SCHEDULES,
                CourseTestHelper.UPDATED_EXAMINATION,
                CourseTestHelper.UPDATED_SUMMARIES,
                CourseTestHelper.PRE_COURSE_ID_SET,
                CourseTestHelper.UPDATED_COURSE_NAME,
                 CourseStatus.OPEN,
                2,
                1L,
                1L
        );

        // when
        course.update(updateCourse);

        // then
        assertThat(course.getCourseExamination()).isEqualTo(CourseTestHelper.UPDATED_EXAMINATION);
        assertThat(course.getSyllabus().getCourseSummaries()).isEqualTo(CourseTestHelper.UPDATED_SUMMARIES);
        assertThat(course.getCourseName()).isEqualTo(CourseTestHelper.UPDATED_COURSE_NAME);
    }

    @Test
    @DisplayName("Course close 후 상태 CLOSED 변경 테스트")
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
    @DisplayName("이미 OPEN 수강 OPEN 시 에러 테스트")
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