package com.sugang.toys.command.student.domain;

import com.sugang.toys.config.JpaRepositoryTestConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class StudentRepositoryTest extends JpaRepositoryTestConfiguration {

    @Autowired
    StudentRepository studentRepository;

    @Test
    void studentCreateTest()
    {

    }
}
