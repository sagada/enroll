package com.sugang.toys.command.student.domain;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@DataJpaTest
class StudentRepositoryTest {

    @Autowired
    StudentRepository studentRepository;

    @Test
    void 학년초과_테스트()
    {
        Student student = Student.create(null, "11", 1L, "11", StudentStatus.ATTENDING, 5);

        studentRepository.save(student);
    }
}