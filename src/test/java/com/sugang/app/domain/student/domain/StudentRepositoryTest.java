package com.sugang.app.domain.student.domain;

import com.sugang.app.config.JpaRepositoryTestConfiguration;
import com.sugang.app.domain.student.domain.Student;
import com.sugang.app.domain.student.domain.StudentRepository;
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
        Student student = new Student("name", 1, 1L, 1L);

        // when
        Student save = studentRepository.save(student);

        // then
        Assertions.assertThat(save).isNotNull();
        Assertions.assertThat(save.getName()).isEqualTo("name");
    }

}
