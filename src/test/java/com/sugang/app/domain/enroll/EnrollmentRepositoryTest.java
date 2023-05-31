package com.sugang.app.domain.enroll;

import com.sugang.app.domain.course.*;
import com.sugang.app.domain.course.validator.CreateCourseValidator;
import com.sugang.app.api.service.enroll.EnrollmentCreateValidator;
import com.sugang.app.domain.student.Student;
import com.sugang.app.domain.student.StudentRepository;
import com.sugang.app.global.JpaRepositoryTestConfiguration;
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
