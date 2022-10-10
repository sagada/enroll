package com.sugang.toys.command.course.domain;

import com.sugang.toys.command.professor.domain.Professor;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.*;
import java.util.Set;

class CourseTest {

    @Test
    void 강의_생성_테스트()
    {
        // given
        CourseSchedule courseSchedule = new CourseSchedule(
                DayOfWeek.MONDAY,
                LocalDateTime.of(2022, Month.MARCH, 23, 11, 30),
                LocalDateTime.of(2022, Month.MARCH, 23, 13, 30),
                "1234"
        );

        // when
        Course course = Course.newCreate(
                null
                , Set.of(courseSchedule)
                , null
                , new Professor(1L, "ddd")
                , "courseName1"
                , 1L
                , 100
        );

        // then
        Assertions.assertNotNull(course);
    }

    @Test
    void 강의_스케줄_중복_테스트()
    {
        // given
        CourseSchedule courseSchedule1 = new CourseSchedule(
                DayOfWeek.MONDAY,
                LocalDateTime.of(LocalDate.of(2022, Month.MARCH, 23), LocalTime.of(11, 20)),
                LocalDateTime.of(LocalDate.of(2022, Month.MARCH, 23), LocalTime.of(20, 30)),
                "1234"
        );

        CourseSchedule courseSchedule2 = new CourseSchedule(
                DayOfWeek.MONDAY,
                LocalDateTime.of(LocalDate.of(2022, Month.MARCH, 23), LocalTime.of(11, 20)),
                LocalDateTime.of(LocalDate.of(2022, Month.MARCH, 23), LocalTime.of(20, 30)),
                "1234"
        );

        // then
        Assertions.assertThrows(
                RuntimeException.class,
                () ->  Course.newCreate(
                        null
                        , Set.of(courseSchedule1, courseSchedule2)
                        , null
                        , new Professor(1L, "ddd")
                        , "courseName1"
                        , 1L
                        , 100
                )
        );
    }
}