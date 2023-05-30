//package com.sugang.app.domain.course.application;
//
//import com.sugang.app.api.service.course.CreateCourseService;
//import com.sugang.app.global.common.exception.ErrorCode;
//import com.sugang.app.api.controller.course.dto.request.CourseCreateRequest;
//import com.sugang.app.api.controller.course.dto.request.CourseScheduleRequest;
//import com.sugang.app.api.controller.course.dto.request.CourseSummaryRequest;
//import com.sugang.app.api.controller.course.dto.response.CreatedCourseResponse;
//import com.sugang.app.config.IntegrationTestConfiguration;
//import org.assertj.core.api.Assertions;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//
//import java.time.DayOfWeek;
//import java.time.LocalDateTime;
//import java.util.Set;
//
//class CreateCourseControllerImplTest extends IntegrationTestConfiguration {
//
//    @Autowired
//    CreateCourseService createCourseService;
//
//    private static CourseCreateRequest given(String courseName)
//    {
//        CourseCreateRequest courseCreateRequest = new CourseCreateRequest(courseName, professorId, bookName, subjectId, courseScheduleRequestSet, courseSummaryRequestSet, midTermExamDate, finalExamDate, preCourseIdSet, score);
//        courseCreateRequest.setCourseName(courseName);
//        courseCreateRequest.setSubjectId(1L);
//        courseCreateRequest.setCourseScheduleRequestSet(
//                Set.of(
//                        new CourseScheduleRequest(DayOfWeek.FRIDAY, LocalDateTime.now(), LocalDateTime.now(), "1234")
//                )
//        );
//        courseCreateRequest.setCourseSummaryRequestSet(
//                Set.of(
//                        new CourseSummaryRequest(1, "content1", "title1"),
//                        new CourseSummaryRequest(2, "content2", "title2")
//                )
//        );
//        courseCreateRequest.setProfessorId(1L);
//        return courseCreateRequest;
//    }
//
//    @Test
//    @DisplayName("course 생성 테스트")
//    void createCourseTest()
//    {
//        // given
//        CourseCreateRequest courseCreateRequest = given("course_name");
//
//        // when
//        CreatedCourseResponse course = createCourseService.createCourse(courseCreateRequest);
//
//        // then
//        Assertions.assertThat(course).isNotNull();
//        Assertions.assertThat(course.getCourseName()).isEqualTo("course_name");
//        Assertions.assertThat(course.getCourseScheduleResultSet()).hasSize(1);
//    }
//
//    @Test
//    @DisplayName("course 생성시에 중복된 이름이면 에러")
//    void duplicateNameCreateCourseErrorTest()
//    {
//        // given
//        CourseCreateRequest courseCreateRequest = given("course1");
//
//        // then
//        Assertions.assertThatThrownBy(
//                () -> createCourseService.createCourse(courseCreateRequest)
//        ).hasMessage(ErrorCode.DUPLICATE_COURSE_NAME.getMessage());
//    }
//}