package com.sugang.toys.command.student.application;

import com.sugang.toys.command.student.controller.StudentEnterCommand;
import com.sugang.toys.command.student.domain.Student;
import com.sugang.toys.command.student.domain.StudentStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class StudentEnterServiceTest {

    @Autowired
    StudentEnterService studentEnterService;

    @Test
    void 학생_입학_테스트()
    {
        StudentEnterCommand command = new StudentEnterCommand("name", "1994-03-30");

        // when
        Student enter = studentEnterService.enter(command);

        // then
        Assertions.assertNotNull(enter);
        Assertions.assertEquals(enter.studentName(), "name");
        Assertions.assertEquals(enter.getStudentStatus(), StudentStatus.ATTENDING);
        Assertions.assertEquals(enter.getGrade(), 1);
        Assertions.assertEquals(enter.birthDay(), "1994-03-30");
        Assertions.assertNotNull(enter.id());
    }

}