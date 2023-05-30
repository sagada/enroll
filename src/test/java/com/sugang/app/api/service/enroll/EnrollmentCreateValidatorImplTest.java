package com.sugang.app.api.service.enroll;

import com.sugang.app.IntegrationTestSupport;
import com.sugang.app.domain.course.domain.Course;
import com.sugang.app.domain.course.domain.CourseRepository;
import com.sugang.app.domain.student.domain.Student;
import com.sugang.app.domain.student.domain.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
class EnrollmentCreateValidatorImplTest extends IntegrationTestSupport
{
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EnrollmentCreateValidatorImpl enrollmentCreateValidator;

    @Mock
    Course course;

    @Mock
    Student student;

    @DisplayName("열리지 않은 수업은 수강 신청할 수 없다.")
    @Test
    void closeCourse()
    {
        // given
        BDDMockito.given(course.isClosed())
                .willReturn(true);

        // when
        Assertions.assertThatThrownBy(() -> enrollmentCreateValidator.validate(course, student))
                // then
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Closed Course!");
    }

    @DisplayName("이수 가능 학점을 초과한 수강신청은 할 수 없다.")
    @Test
    void studentStatus()
    {
        // given
        BDDMockito.given(course.isClosed())
                .willReturn(false);

        BDDMockito.given(course.getScore())
                .willReturn(4);

        BDDMockito.given(student.getAvailableScore())
                .willReturn(3);

        // when
        Assertions.assertThatThrownBy(() -> enrollmentCreateValidator.validate(course, student))
                // then
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("student status fail!");
    }
}