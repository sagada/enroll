package com.sugang.app.api.service.course;

import com.sugang.app.TestContainerIntegrationTestSupport;
import com.sugang.app.api.controller.course.dto.request.CourseScheduleRequest;
import com.sugang.app.api.controller.course.dto.request.CourseSummaryRequest;
import com.sugang.app.api.controller.course.dto.response.CreatedCourseResponse;
import com.sugang.app.api.service.course.request.CourseCreateServiceRequest;
import org.assertj.core.api.Assertions;
import org.assertj.core.groups.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Set;

@Transactional
class CreateCourseServiceImplTest extends TestContainerIntegrationTestSupport {

    @Autowired
    CreateCourseService createCourseService;

    @DisplayName("수업을 생성할 수 있다.")
    @Test
    void createCourse()
    {
        // given
        LocalDateTime startTime = LocalDateTime.of(2023, 4, 14, 14, 30);
        LocalDateTime endTime = LocalDateTime.of(2023,4,15,16,0);
        CourseCreateServiceRequest courseCreateRequest = CourseCreateServiceRequest.builder()
                .courseName("courseName")
                .bookName("bookName")
                .courseSummaryRequestSet(
                        Set.of(
                                CourseSummaryRequest.builder()
                                        .content("content")
                                        .title("title")
                                        .week(1)
                                        .build()
                        )
                )
                .courseScheduleRequestSet(
                        Set.of(
                                CourseScheduleRequest.builder()
                                        .from(startTime)
                                        .to(endTime)
                                        .roomNumber("1004")
                                        .build()
                        )
                )
                .preCourseIdSet(Set.of(1L,2L))
                .subjectId(1L)
                .score(1)
                .professorId(1L)
                .build();

        // when
        CreatedCourseResponse course = createCourseService.createCourse(courseCreateRequest);

        // then
        Assertions.assertThat(course.getCourseId()).isNotNull();

        Assertions.assertThat(course.getCourseScheduleResultSet())
                .isNotEmpty()
                .extracting("startTime", "endTime")
                .contains(Tuple.tuple(startTime, endTime));

        Assertions.assertThat(course)
                .extracting("courseName", "professorId")
                .containsExactly("courseName", 1L);
    }

}