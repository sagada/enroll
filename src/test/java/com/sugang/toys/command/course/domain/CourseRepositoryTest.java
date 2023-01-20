package com.sugang.toys.command.course.domain;

import com.google.common.collect.Sets;
import com.sugang.toys.config.JpaRepositoryTestConfiguration;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Course Repository Test")
public class CourseRepositoryTest extends JpaRepositoryTestConfiguration {

    @Autowired
    CourseRepository courseRepository;

    static Set<CourseSchedule> givenCourseSchedules()
    {
        CourseSchedule courseSchedule1 = new CourseSchedule(
                LocalDateTime.of(LocalDate.of(2022, Month.MARCH, 23), LocalTime.of(11, 20)),
                LocalDateTime.of(LocalDate.of(2022, Month.MARCH, 23), LocalTime.of(20, 30)),
                "4567"
        );

        CourseSchedule courseSchedule2 = new CourseSchedule(
                LocalDateTime.of(LocalDate.of(2022, Month.MARCH, 23), LocalTime.of(11, 20)),
                LocalDateTime.of(LocalDate.of(2022, Month.MARCH, 23), LocalTime.of(20, 30)),
                "1234"
        );

        return Sets.newHashSet(courseSchedule1, courseSchedule2);
    }

    @Disabled
    @DisplayName("Course 생성 테스트")
    @Test
    void create()
    {
        // given
        Set<CourseSchedule> courseSchedules = givenCourseSchedules();
        Set<CourseSummary> courseSummaries = Sets.newHashSet(
                new CourseSummary(1, "content", "title"),
                new CourseSummary(2, "content1", "title1")
        );

        CourseExamination courseExamination = new CourseExamination(
                LocalDateTime.of(2022, Month.JANUARY, 13, 13, 15),
                LocalDateTime.of(2022, Month.SEPTEMBER, 13, 14, 15)
        );

        Course course = new Course(
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

        // when
        Course savedCourse = courseRepository.save(course);

        // then
        assertThat(savedCourse).isNotNull();
        assertThat(savedCourse.getCourseStatus()).isEqualTo(CourseStatus.OPEN);
        assertThat(savedCourse.getCourseName().getValue()).isEqualTo("courseName");
        assertThat(savedCourse.getScore()).isEqualTo(2);
        assertThat(savedCourse.getPrerequisiteCourse()).extracting(PrerequisiteCourse::getPreCourseSeqList)
                .isEqualTo(Set.of(1L, 2L));

        CourseSummaries savedCourseCourseSummaries = savedCourse.getCourseSummaries();
        assertThat(savedCourseCourseSummaries.getCourseSummaries()).hasSize(2);
        assertThat(savedCourseCourseSummaries.getCourseSummaries())
                .isEqualTo(
                        Set.of(
                                new CourseSummary(1, "content", "title")
                                , new CourseSummary(2, "content1", "title1")
                        )
                );

        CourseSchedules savedCourseSchedules = savedCourse.getCourseSchedules();
        assertThat(savedCourseSchedules.getCourseScheduleSet()).hasSize(2);
        assertThat(savedCourseSchedules.getCourseScheduleSet()).isEqualTo(courseSchedules);
    }
}
