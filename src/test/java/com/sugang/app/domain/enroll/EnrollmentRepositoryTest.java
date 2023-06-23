package com.sugang.app.domain.enroll;

import com.sugang.app.JpaRepositoryTestConfiguration;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

@DisplayName("수강신청 레파지토리 테스트")
public class EnrollmentRepositoryTest extends JpaRepositoryTestConfiguration {

    @Autowired
    EnrollmentRepository enrollmentRepository;

    @DisplayName("수강신청을 할 수 있다.")
    @Test
    void createTest()
    {
        Enrollment enrollment = Enrollment.enroll(1L, 2L);

        Enrollment save = enrollmentRepository.save(enrollment);

        Assertions.assertThat(save).isNotNull();
        Assertions.assertThat(save.getEnrolmentStatus()).isEqualTo(EnrolmentStatus.PROPOSE);
        Assertions.assertThat(save.getCourseId()).isEqualTo(1L);
        Assertions.assertThat(save.getStudentId()).isEqualTo(2L);
    }
}
