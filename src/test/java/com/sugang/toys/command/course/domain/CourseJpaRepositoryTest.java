package com.sugang.toys.command.course.domain;

import com.sugang.toys.command.professor.domain.Professor;
import org.assertj.core.api.Assertions;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.time.DayOfWeek;

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
                "14:00:00",
                "16:00:00",
                "4567"
        );

        CourseSchedule courseSchedule2 = new CourseSchedule(
                DayOfWeek.WEDNESDAY,
                "15:00:00",
                "17:00:00",
                "1234"
        );

        Course course = Course.create(
                null,
                Lists.newArrayList(courseSchedule1, courseSchedule2)
                , null
                , new Professor()
                , "courseName1"
                , 100
        );

        // when
        Course save = courseRepository.save(course);

        // then
        Assertions.assertThat(save).isNotNull();

        CourseSchedules courseSchedules = save.getCourseSchedules();

        Assertions.assertThat(courseSchedules.getCourseScheduleList()).hasSize(2);
        Assertions.assertThat(courseSchedules.getCourseScheduleList())
                .containsExactlyInAnyOrder(courseSchedule1, courseSchedule2);
    }
}
