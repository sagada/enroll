package com.sugang.toys.command.course.application;

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
public class CourseAddServiceTest {

    @Autowired
    CourseAddService courseAddService;

    @Test
    void addCourseTest()
    {
        AddCourseRequest addCourseRequest = new AddCourseRequest();
        addCourseRequest.setCourseName("course_name");
        addCourseRequest.setProfessorId(1111L);
        addCourseRequest.setDepartmentId(99999L);
        addCourseRequest.setMaxCourseStudentCount(10);
        addCourseRequest.setCourseScheduleSet(Set.of(new AddCourseRequest.CourseScheduleDto(
                DayOfWeek.FRIDAY,
                LocalDateTime.of(2022, Month.DECEMBER, 11, 22, 3),
                LocalDateTime.of(2022, Month.DECEMBER, 11, 22, 3),
                "1234"
        )));

        // when
        courseAddService.addCourse(addCourseRequest);
    }
}
