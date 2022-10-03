package com.sugang.toys.command.course.application;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.transaction.annotation.Transactional;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

@Transactional
@Sql(scripts = {"classpath:sql/init.sql"})
@SpringBootTest
public class OpenCourseServiceIntegrationTest {

    @Autowired
    OpenCourseService openCourseService;

    @Test
    void addCourseTest()
    {
        // given
        OpenCourseRequest openCourseRequest = new OpenCourseRequest()
                .setCourseName("course_name")
                .setProfessorId(1111L)
                .setDepartmentId(99999L)
                .setMaxCourseStudentCount(10)
                .setCourseScheduleSet(
                        Set.of(
                                new OpenCourseRequest.CourseScheduleDto(
                                    DayOfWeek.FRIDAY,
                                    LocalDateTime.of(2022, Month.DECEMBER, 11, 22, 3),
                                    LocalDateTime.of(2022, Month.DECEMBER, 11, 22, 3),
                        "1234"
        )));

        // when
        Long id = openCourseService.openCourse(openCourseRequest);

        // then
        Assertions.assertNotNull(id);
    }
}
