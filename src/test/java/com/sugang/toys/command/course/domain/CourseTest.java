package com.sugang.toys.command.course.domain;

import com.sugang.toys.command.professor.domain.Professor;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.DayOfWeek;

class CourseTest {

    @Test
    void 강의_생성_테스트()
    {
        // given
        CourseSchedule courseSchedule = new CourseSchedule(
                DayOfWeek.MONDAY,
                "14:00:00",
                "16:00:00",
                "1234"
        );

        // when
        Course course = Course.create(
                null
                , Lists.newArrayList(courseSchedule)
                , null
                , new Professor()
                , "courseName1"
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
                "14:00:00",
                "16:00:00",
                "1234"
        );

        CourseSchedule courseSchedule2 = new CourseSchedule(
                DayOfWeek.MONDAY,
                "14:00:00",
                "16:00:00",
                "1234"
        );

        // then
        Assertions.assertThrows(
                RuntimeException.class,
                () ->  Course.create(
                        null
                        , Lists.newArrayList(courseSchedule1, courseSchedule2)
                        , null
                        , new Professor()
                        , "courseName1"
                        , 100
                )
        );
    }
}