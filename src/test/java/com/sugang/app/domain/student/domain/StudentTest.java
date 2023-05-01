package com.sugang.app.domain.student.domain;

import com.sugang.app.domain.student.domain.Student;
import com.sugang.app.domain.student.domain.StudentRegistrationInfo;
import com.sugang.app.domain.student.domain.exception.StudentException;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StudentTest {

    @Test
    void registerTest()
    {
        Student student = new Student("studentName1", 1, 1L, 1L);
        LocalDateTime now = LocalDateTime.now();
        StudentRegistrationInfo studentRegistrationInfo = new StudentRegistrationInfo(
                true,
                now
        );
        student.register(studentRegistrationInfo);
        List<StudentRegistrationInfo> studentRegistrationInfos = student.getStudentRegistrationInfos();
        assertThat(studentRegistrationInfos).hasSize(1);
        assertThat(studentRegistrationInfos.get(0).isRegistered()).isTrue();
        assertThat(studentRegistrationInfos.get(0).getRegisterDate()).isNotNull();
    }

    @Test
    void academicYearBoundErrorTest()
    {
        assertThatThrownBy(
                    () -> new Student("studentName1", 12, 1L, 1L)
                )
                .isInstanceOf(StudentException.class)
                .hasMessage("academicYear error");
    }
}