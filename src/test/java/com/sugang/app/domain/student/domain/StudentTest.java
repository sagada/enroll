package com.sugang.app.domain.student.domain;

import com.sugang.app.domain.student.domain.exception.StudentException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StudentTest {

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