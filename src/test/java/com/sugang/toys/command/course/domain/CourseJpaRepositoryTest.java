package com.sugang.toys.command.course.domain;

import com.sugang.toys.command.department.domain.Department;
import com.sugang.toys.command.professor.domain.Professor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.*;
import java.util.Set;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
public class CourseJpaRepositoryTest {

    @Autowired
    CourseRepository courseRepository;

    @Test
    void create()
    {
        // given
        CourseSchedule courseSchedule1 = new CourseSchedule(
                DayOfWeek.MONDAY,
                LocalDateTime.of(LocalDate.of(2022, Month.MARCH, 23), LocalTime.of(11, 20)),
                LocalDateTime.of(LocalDate.of(2022, Month.MARCH, 23), LocalTime.of(20, 30)),
                "4567"
        );

        CourseSchedule courseSchedule2 = new CourseSchedule(
                DayOfWeek.WEDNESDAY,
                LocalDateTime.of(LocalDate.of(2022, Month.MARCH, 23), LocalTime.of(11, 20)),
                LocalDateTime.of(LocalDate.of(2022, Month.MARCH, 23), LocalTime.of(20, 30)),
                "1234"
        );

        Course course = Course.create(
                null,
                Set.of(courseSchedule1, courseSchedule2)
                , null
                , new Professor()
                , "courseName1"
                , new Department()
                , 100
        );

        // when
        Course savedCourse = courseRepository.save(course);

        // then
        Assertions.assertThat(savedCourse).isNotNull();
        Assertions.assertThat(savedCourse.getName()).isEqualTo("courseName1");

        CourseSchedules courseSchedules = savedCourse.getCourseSchedules();

        Assertions.assertThat(courseSchedules.getCourseScheduleList()).hasSize(2);
        Assertions.assertThat(courseSchedules.getCourseScheduleList()).containsExactlyInAnyOrder(courseSchedule1, courseSchedule2);
    }
}
