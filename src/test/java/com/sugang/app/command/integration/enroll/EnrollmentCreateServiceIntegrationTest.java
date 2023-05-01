package com.sugang.app.command.integration.enroll;

import com.sugang.app.domain.enroll.application.EnrollmentCreateServiceImpl;
import com.sugang.app.config.IntegrationTestConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class EnrollmentCreateServiceIntegrationTest extends IntegrationTestConfiguration {

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
