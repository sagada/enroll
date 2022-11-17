package com.sugang.toys.command.student.domain;

import com.sugang.toys.config.JpaRepositoryTestConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentRepositoryTest extends JpaRepositoryTestConfiguration {

    @Autowired
    StudentRepository studentRepository;

    @Test
    void studentCreateTest()
    {
        Student student = Student.create("name");

        Student save = studentRepository.save(student);

        Assertions.assertThat(save).isNotNull();
    }
}
