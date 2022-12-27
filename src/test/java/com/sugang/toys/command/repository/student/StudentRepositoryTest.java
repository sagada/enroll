package com.sugang.toys.command.repository.student;

import com.sugang.toys.command.student.domain.Student;
import com.sugang.toys.command.student.domain.StudentRepository;
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
        // given
        Student student = Student.create("name");

        // when
        Student save = studentRepository.save(student);

        // then
        Assertions.assertThat(save).isNotNull();
        Assertions.assertThat(save.getName()).isEqualTo("name");
    }

}
