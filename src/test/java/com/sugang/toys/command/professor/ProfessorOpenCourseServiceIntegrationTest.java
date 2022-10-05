package com.sugang.toys.command.professor;

import com.sugang.toys.command.professor.application.ProfessorCourseScheduleDto;
import com.sugang.toys.command.professor.application.ProfessorOpenCourseRequest;
import com.sugang.toys.command.professor.application.ProfessorOpenCourseService;
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
public class ProfessorOpenCourseServiceIntegrationTest {

    @Autowired
    ProfessorOpenCourseService professorOpenCourseService;

    @Test
    void addCourseTest()
    {
        // given
        ProfessorOpenCourseRequest professorOpenCourseRequest = new ProfessorOpenCourseRequest()
                .setCourseName("course_name")
                .setProfessorId(1111L)
                .setDepartmentId(99999L)
                .setMaxCourseStudentCount(10)
                .setCourseScheduleSet(
                        Set.of(
                                new ProfessorCourseScheduleDto(
                                    DayOfWeek.FRIDAY,
                                    LocalDateTime.of(2022, Month.DECEMBER, 11, 22, 3),
                                    LocalDateTime.of(2022, Month.DECEMBER, 11, 22, 3),
                        "1234"
        )));

        // when
        Long id = professorOpenCourseService.professorOpenCourse(professorOpenCourseRequest);

        // then
        Assertions.assertNotNull(id);
    }
}
