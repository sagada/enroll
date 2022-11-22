package com.sugang.toys.command.enroll.domain;

import com.sugang.toys.command.course.domain.*;
import com.sugang.toys.command.department.domain.Department;
import com.sugang.toys.command.enroll.application.EnrollmentCreateValidate;
import com.sugang.toys.command.professor.domain.Professor;
import com.sugang.toys.command.student.domain.Student;
import com.sugang.toys.command.student.domain.StudentRepository;
import com.sugang.toys.config.JpaRepositoryTestConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.List;
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
    Department department;

    @Mock
    Professor professor;

    @Mock
    EnrollmentCreateValidate enrollmentCreateValidate;

    @Test
    void createTest()
    {
        Student student = new Student("name");

        Course course = Course.createCourse(
                Set.of(new CourseSchedule(DayOfWeek.FRIDAY, LocalDateTime.now(), LocalDateTime.now(), "1004"))
                , Set.of(new CourseSummary(1, "content", "title"))
                , professor
                ,"courseName"
                , department
                , "bookName"
                , 1
                , createCourseValidator
        );

        studentRepository.save(student);
        courseRepository.save(course);

        Enrollment enrollment = Enrollment.enroll(
                course.getId()
                , student.getId()
                , enrollmentCreateValidate
        );

        Enrollment save = enrollmentRepository.save(enrollment);

        Assertions.assertThat(save).isNotNull();
        Assertions.assertThat(save.getEnrolmentStatus()).isEqualTo(EnrolmentStatus.PROPOSE);
        Assertions.assertThat(save.getCourseId()).isNotNull();
        Assertions.assertThat(save.getEnrollStudent()).isNotNull();
    }

    @Test
    void enrollFindTest()
    {
        List<Long> enrollmentListByStudentId = enrollmentRepository.findEnrollmentListByStudentId(1L);
        System.out.println("fined :" + enrollmentListByStudentId);
    }
}
