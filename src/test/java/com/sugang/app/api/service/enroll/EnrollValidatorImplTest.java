package com.sugang.app.api.service.enroll;

import com.sugang.app.domain.course.Course;
import com.sugang.app.domain.course.CourseRepository;
import com.sugang.app.domain.course.CourseTestHelper;
import com.sugang.app.domain.course.infra.CreateCourseValidatorImpl;
import com.sugang.app.api.service.course.CourseScheduleOverlapCheckService;
import com.sugang.app.domain.enroll.EnrollmentRepository;
import com.sugang.app.domain.student.Student;
import com.sugang.app.global.exception.ApiException;
import com.sugang.app.global.exception.ErrorCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

@ExtendWith(MockitoExtension.class)
class EnrollValidatorImplTest {

    @Mock
    CreateCourseValidatorImpl createCourseValidator;

    @Mock
    EnrollmentRepository enrollmentRepository;

    @Mock
    CourseRepository courseRepository;

    @Mock
    CourseScheduleOverlapCheckService courseScheduleOverlapCheckService;

    @InjectMocks
    EnrollValidatorImpl enrollValidator;

    @DisplayName("닫힌 수강은 등록 할 수 없다.")
    @Test
    void closedCourse()
    {
        // given
        Student student = Student.createStudent(
                "IMac",
                "e@naver.com",
                1,
                17,
                1L,
                1L
        );

        Course course = Course.createCourse(
                CourseTestHelper.SCHEDULES,
                CourseTestHelper.SUMMARIES,
                CourseTestHelper.EXAMINATION,
                CourseTestHelper.PRE_COURSE_ID_SET,
                1L,
                1L,
                CourseTestHelper.COURSE_NAME,
                3,
                createCourseValidator,
                10
        );

        course.close();

        // when
        Assertions.assertThatThrownBy(() -> enrollValidator.validate(course, student))
                // then
                .isInstanceOf(ApiException.class)
                .hasMessage(ErrorCode.CLOSED_COURSE.getMessage());
    }

    @DisplayName("중복된 수강 신청을 할 수 없다.")
    @Test
    void duplicatedCourse()
    {
        // given
        Student student = Mockito.mock(Student.class);
        Course course = Mockito.mock(Course.class);

        BDDMockito.given(course.getId()).willReturn(1L);
        BDDMockito.given(course.availableAddStudent()).willReturn(true);

        BDDMockito.given(enrollmentRepository.findCourseIdListByStudentId(Mockito.any()))
                        .willReturn(List.of(1L, 2L));
        // when
        Assertions.assertThatThrownBy(() -> enrollValidator.validate(course, student))
                // then
                .isInstanceOf(ApiException.class)
                .hasMessage(ErrorCode.DUPLICATE_COURSE.getMessage());
    }

    @DisplayName("수강 하려고 하는 수강의 스케줄은 이미 수강 신청한 수업 시간과 겹칠 수 없다.")
    @Test
    void overlapCourseSchedule()
    {
        // given
        Student student = Mockito.mock(Student.class);
        Course course = Mockito.mock(Course.class);

        BDDMockito.given(course.availableAddStudent()).willReturn(true);
        BDDMockito.given(courseScheduleOverlapCheckService.isOverlap(Mockito.any(), Mockito.any()))
                        .willReturn(true);
        // when
        Assertions.assertThatThrownBy(() -> enrollValidator.validate(course, student))
                // then
                .isInstanceOf(ApiException.class)
                .hasMessage(ErrorCode.DUPLICATE_COURSE_TIME.getMessage());
    }

}