package com.sugang.toys.command.student.domain;

import com.sugang.toys.command.student.domain.exception.StudentErrorCode;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class StudentEntityTest {

    @Test
    void 학생_생성자_테스트()
    {
        // given
        String studentName = "이름";
        String birthDay = "2022-02-22";
        // when
        Student student = Student.create(null, studentName, 1L, birthDay, StudentStatus.ATTENDING, 1);

        // then
        Assertions.assertThat(student).isNotNull();
        Assertions.assertThat(student.studentName()).isEqualTo(studentName);
        Assertions.assertThat(student.birthDay()).isEqualTo(birthDay);
        Assertions.assertThat(student.getGrade()).isEqualTo(1);
    }

    @Test
    void 학생_업데이트_테스트()
    {
        // given
        String studentName = "변경_이름";
        String birthDay = "2022-03-29";

        Student student = Student.create(null, "이름", 1L, "2022-01-01", StudentStatus.ATTENDING, 1);
        Student updated = Student.create(null, studentName, 1L, birthDay, StudentStatus.ATTENDING, 1);

        // when
        student.update(updated);

        // then
        Assertions.assertThat(student.birthDay()).isEqualTo(birthDay);
        Assertions.assertThat(student.studentName()).isEqualTo(studentName);
    }

    @Test
    void 퇴학_학생_업데이트_실패_테스트()
    {
        // given
        String studentName = "변경_이름";
        String birthDay = "2022-03-29";

        Student student = Student.create(null, "이름", 1L, "2022-01-01", StudentStatus.EXPEL, 1);
        Student updated = Student.create(null, studentName, 1L, birthDay, StudentStatus.EXPEL, 1);

        // then
        Assertions.assertThatThrownBy(() -> student.update(updated))
                .hasMessage(StudentErrorCode.EXPEL_UPDATE_ERROR.getMessage());
    }

}
