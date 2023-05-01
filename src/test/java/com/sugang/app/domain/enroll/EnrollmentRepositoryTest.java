package com.sugang.app.domain.enroll;

import com.sugang.app.domain.course.domain.*;
import com.sugang.app.domain.course.domain.validator.CreateCourseValidator;
import com.sugang.app.domain.enroll.application.EnrollmentCreateValidator;
import com.sugang.app.domain.enroll.domain.Enrollment;
import com.sugang.app.domain.enroll.domain.EnrollmentRepository;
import com.sugang.app.domain.enroll.domain.EnrolmentStatus;
import com.sugang.app.domain.student.domain.Student;
import com.sugang.app.domain.student.domain.StudentRepository;
import com.sugang.app.config.JpaRepositoryTestConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDateTime;
import java.util.Set;

public class EnrollmentRepositoryTest extends JpaRepositoryTestConfiguration {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @Mock
    CreateCourseValidator createCourseValidator;

    @Mock
    EnrollmentCreateValidator enrollmentCreateValidator;

    @Test
    void createTest()
    {
        Student student = new Student("name", 1, 1L, 1L);

        Course course = Course.createCourse(
                Set.of(new CourseSchedule(LocalDateTime.now(), LocalDateTime.now(), "1004"))
                , Set.of(new CourseSummary(1, "content", "title"))
                , new CourseExamination(LocalDateTime.now(), LocalDateTime.now())
                , Set.of(1L, 2L)
                ,1L
                , 1L
                , new CourseName("courseName")
                , 1
                , createCourseValidator
        );

        studentRepository.save(student);
        courseRepository.save(course);

        Enrollment enrollment = Enrollment.enroll(
                course.getId()
                , student.getId()
                , enrollmentCreateValidator
        );

        Enrollment save = enrollmentRepository.save(enrollment);

        Assertions.assertThat(save).isNotNull();
        Assertions.assertThat(save.getEnrolmentStatus()).isEqualTo(EnrolmentStatus.PROPOSE);
        Assertions.assertThat(save.getCourseId()).isNotNull();
        Assertions.assertThat(save.getEnrollStudent()).isNotNull();
    }
}
