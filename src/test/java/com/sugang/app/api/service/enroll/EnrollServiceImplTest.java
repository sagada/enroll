package com.sugang.app.api.service.enroll;

import com.sugang.app.TestContainerIntegrationTestSupport;
import com.sugang.app.api.service.enroll.response.EnrollServiceResponse;
import com.sugang.app.domain.course.Course;
import com.sugang.app.domain.course.CourseRepository;
import com.sugang.app.domain.course.CourseTestHelper;
import com.sugang.app.domain.course.infra.CreateCourseValidatorImpl;
import com.sugang.app.domain.student.Student;
import com.sugang.app.domain.student.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

@Transactional
class EnrollServiceImplTest extends TestContainerIntegrationTestSupport {

    @Autowired
    EnrollServiceImpl enrollService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CreateCourseValidatorImpl createCourseValidator;

    @DisplayName("수강신청을 할 수 있다.")
    @Test
    void enroll()
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

        courseRepository.save(course);
        studentRepository.save(student);

        // when
        EnrollServiceResponse enroll = enrollService.enroll(course.getId(), student.getId());

        // then
        Assertions.assertThat(enroll).isNotNull()
                .extracting(
                    EnrollServiceResponse::getCourseName,
                    EnrollServiceResponse::getCourseId,
                    EnrollServiceResponse::getStudentId,
                    EnrollServiceResponse::getStudentName)
                .contains(
                    CourseTestHelper.COURSE_NAME.getValue(),
                    course.getId(),
                    student.getId(),
                    "IMac");

        Assertions.assertThat(course.getAvailStudentCount()).isEqualTo(9);
        Assertions.assertThat(student.getAvailableScore()).isEqualTo(14);
    }

    @DisplayName("수강 가능 신청수가 초과 된 수강은 등록이 되지 않는다.")
    @Test
    void availableStudentCountZero()
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
                0
        );

        courseRepository.save(course);
        studentRepository.save(student);

        // when
        Assertions.assertThatThrownBy(() -> enrollService.enroll(course.getId(), student.getId()))
                // then
                .isInstanceOf(IllegalStateException.class)
                .hasMessage("Exceeded number of students");
    }
}