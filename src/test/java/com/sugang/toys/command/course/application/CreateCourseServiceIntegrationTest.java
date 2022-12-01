package com.sugang.toys.command.course.application;

import com.sugang.toys.command.common.exception.ErrorCode;
import com.sugang.toys.command.course.application.dto.CourseCreateCommand;
import com.sugang.toys.command.course.application.dto.CourseScheduleRequest;
import com.sugang.toys.command.course.application.dto.CourseSummaryRequest;
import com.sugang.toys.command.course.application.dto.CreatedCourseResult;
import com.sugang.toys.config.ApplicationIntegrationTestConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Set;

public class CreateCourseServiceIntegrationTest extends ApplicationIntegrationTestConfiguration
{
    @Autowired
    CreateCourseService createCourseService;

    @Test
    @DisplayName("course 생성 테스트")
    void createCourseTest()
    {
        // given
        CourseCreateCommand courseCreateCommand = new CourseCreateCommand()
                .setCourseName("course_name")
                .setCourseScheduleSet(
                        Set.of(
                            new CourseScheduleRequest(DayOfWeek.FRIDAY, LocalDateTime.now(), LocalDateTime.now(), "1234")
                        )
                )
                .setCourseSummaryRequestSet(
                        Set.of(
                            new CourseSummaryRequest(1, "content1", "title1"),
                            new CourseSummaryRequest(2, "content2", "title2")
                        )
                )
                .setDepartmentId(1L)
                .setProfessorId(1L);

        // when
        CreatedCourseResult course = createCourseService.createCourse(courseCreateCommand);

        // then
        Assertions.assertThat(course).isNotNull();
        Assertions.assertThat(course.getCourseName()).isEqualTo("course_name");
        Assertions.assertThat(course.getCourseScheduleResultSet()).hasSize(1);
    }

    @Test
    @DisplayName("course 생성시에 중복된 이름이면 에러")
    void duplicateNameCreateCourseErrorTest()
    {
        // given
        CourseCreateCommand courseCreateCommand = new CourseCreateCommand()
                .setCourseName("course1")
                .setCourseScheduleSet(
                        Set.of(
                                new CourseScheduleRequest(DayOfWeek.FRIDAY, LocalDateTime.now(), LocalDateTime.now(), "1234")
                        )
                )
                .setCourseSummaryRequestSet(
                        Set.of(
                                new CourseSummaryRequest(1, "content1", "title1"),
                                new CourseSummaryRequest(2, "content2", "title2")
                        )
                )
                .setDepartmentId(1L)
                .setProfessorId(1L);

        // then
        Assertions.assertThatThrownBy(
                () -> createCourseService.createCourse(courseCreateCommand)
        ).hasMessage(ErrorCode.DUPLICATE_COURSE_NAME.getMessage());
    }
}
