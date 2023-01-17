package com.sugang.toys.command.course.domain;

import com.google.common.collect.Sets;
import com.sugang.toys.config.JpaRepositoryTestConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Set;

@DisplayName("Course Repository Test")
public class CourseJpaRepositoryTest extends JpaRepositoryTestConfiguration {

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
        Course course = new Course();

        // when
        Course savedCourse = courseRepository.save(course);

        // then
        Assertions.assertThat(savedCourse).isNotNull();
        Assertions.assertThat(savedCourse.getCourseName()).isEqualTo(new CourseName("courseName"));
        Assertions.assertThat(savedCourse.getScore()).isEqualTo(10);

        CourseSchedules savedCourseSchedules = savedCourse.getCourseSchedules();
        Set<CourseSummary> courseSummaries = savedCourse.getCourseSummaries().getCourseSummaries();
        Assertions.assertThat(courseSummaries).hasSize(1);
        Assertions.assertThat(courseSummaries).isEqualTo(Set.of(new CourseSummary(1, "content", "title")));
        Assertions.assertThat(savedCourseSchedules.getCourseScheduleSet()).hasSize(2);
        Assertions.assertThat(savedCourseSchedules.getCourseScheduleSet()).isEqualTo(courseSchedules);
    }
}
