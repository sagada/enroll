package com.sugang.toys.command.enroll.application;

import com.sugang.toys.config.TestContainerConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EnrollmentCreateServiceImplIntegrationTest extends TestContainerConfiguration {

    @Autowired
    EnrollmentCreateServiceImpl enrollmentServiceImpl;

    @Test
    void enrollTest()
    {
        // given
        long studentId = 1L;
        long courseId = 1L;

        // when
        Long enroll = enrollmentServiceImpl.enroll(courseId, studentId);

        // then
        Assertions.assertThat(enroll).isNotNull();
    }
}
