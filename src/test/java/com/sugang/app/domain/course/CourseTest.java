package com.sugang.app.domain.course;

import com.sugang.app.domain.course.exception.CourseException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

@DisplayName("수업 도메인 테스트")
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
    @DisplayName("수업이 생성 될 수 있다.")
    void createTest()
    {
        Course course = givenCourse();

        assertThat(course.getCourseExamination().getMidTermDate())
                .isEqualTo(LocalDateTime.of(2022, Month.JANUARY, 13, 13, 15));
        assertThat(course.getCourseStatus()).isEqualTo(CourseStatus.OPEN);
        assertThat(course.getCourseName()).isEqualTo(CourseTestHelper.COURSE_NAME);
        assertThat(course.getSyllabus()).extracting(Syllabus::getCourseSummaries).isEqualTo(CourseTestHelper.SUMMARIES);
        assertThat(course.getPrerequisiteCourse()).extracting(PrerequisiteCourse::getPreCourseSeqList).isEqualTo(Set.of(1L, 2L));
        assertThat(course.getCourseScheduleSet()).hasSize(1);
    }

    @Test
    @DisplayName("올바르지 않은 시험날짜는 생성이 되지 않는다.")
    void courseExaminationErrorTest()
    {
        assertThatThrownBy(() -> new CourseExamination(
                LocalDateTime.of(2022, Month.SEPTEMBER, 13, 14, 15),
                LocalDateTime.of(2022, Month.JANUARY, 13, 13, 15)
        )).isInstanceOf(CourseException.class).hasMessage("examination date error");
    }

    @Test
    @DisplayName("수업이 수정 될 수 있다.")
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
    @DisplayName("수업은 닫힐 수 있다.")
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
    @DisplayName("이미 열린 수업은 다시 열수 없다.")
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