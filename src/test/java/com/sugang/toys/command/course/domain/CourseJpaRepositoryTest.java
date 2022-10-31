package com.sugang.toys.command.course.domain;

import com.sugang.toys.command.config.JpaRepositoryTestConfiguration;
import com.sugang.toys.command.department.domain.Department;
import com.sugang.toys.command.department.domain.Division;
import com.sugang.toys.command.professor.domain.Professor;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.*;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class CourseJpaRepositoryTest extends JpaRepositoryTestConfiguration {

    @Autowired
    CourseRepository courseRepository;

    @MockBean
    CreateCourseValidator createCourseValidator;

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

        Course course = Course.createCourse(
                Set.of(courseSchedule1, courseSchedule2)
                , Set.of(new CourseSummary(1, "content", "title"))
                , new Professor(1L, "professorName")
                , "courseName1"
                , new Department(1L, "it", Division.IT, "1022", "2022")
                , "bookName1"
                , createCourseValidator
        );

        // when
        Course savedCourse = courseRepository.save(course);

        // then
        Assertions.assertThat(savedCourse).isNotNull();
        Assertions.assertThat(savedCourse.getName()).isEqualTo("courseName1");
        CourseSchedules courseSchedules = savedCourse.getCourseSchedules();

        Set<CourseSummary> courseSummaries = savedCourse.getCourseSummaries().getCourseSummaries();
        Assertions.assertThat(courseSummaries).hasSize(1);
        Assertions.assertThat(courseSummaries).isEqualTo(Set.of(new CourseSummary(1, "content", "title")));
        Assertions.assertThat(courseSchedules.getCourseScheduleSet()).hasSize(2);
        Assertions.assertThat(courseSchedules.getCourseScheduleSet()).containsExactlyInAnyOrder(courseSchedule1, courseSchedule2);
    }

    @Test
    void update()
    {

    }

}
