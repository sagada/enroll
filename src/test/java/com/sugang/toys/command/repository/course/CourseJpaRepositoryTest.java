package com.sugang.toys.command.repository.course;

import com.google.common.collect.Sets;
import com.sugang.toys.command.course.domain.*;
import com.sugang.toys.config.JpaRepositoryTestConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.*;
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

    @DisplayName("Course 생성 테스트")
    @Test
    void create()
    {
        // given
        Set<CourseSchedule> courseSchedules = givenCourseSchedules();
        Course course = Course.Create(
                courseSchedules
                , Set.of(new CourseSummary(1, "content", "title"))
                ,null
                , 1L
                , "courseName"
                , CourseStatus.OPEN
                , 10
        );

        // when
        Course savedCourse = courseRepository.save(course);

        // then
        Assertions.assertThat(savedCourse).isNotNull();
        Assertions.assertThat(savedCourse.getName()).isEqualTo("courseName");
        Assertions.assertThat(savedCourse.getScore()).isEqualTo(10);

        CourseSchedules savedCourseSchedules = savedCourse.getCourseSchedules();
        Set<CourseSummary> courseSummaries = savedCourse.getCourseSummaries().getCourseSummaries();
        Assertions.assertThat(courseSummaries).hasSize(1);
        Assertions.assertThat(courseSummaries).isEqualTo(Set.of(new CourseSummary(1, "content", "title")));
        Assertions.assertThat(savedCourseSchedules.getCourseScheduleSet()).hasSize(2);
        Assertions.assertThat(savedCourseSchedules.getCourseScheduleSet()).isEqualTo(courseSchedules);
    }
}
