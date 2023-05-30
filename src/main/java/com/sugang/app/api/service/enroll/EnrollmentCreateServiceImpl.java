package com.sugang.app.api.service.enroll;

import com.sugang.app.domain.enroll.domain.Enrollment;
import com.sugang.app.domain.enroll.domain.EnrollmentRepository;
import com.sugang.app.global.common.event.EnrollRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
public class EnrollmentCreateServiceImpl implements EnrollmentCreateService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentCreateValidator enrollmentCreateValidator;
    private final ApplicationEventPublisher publisher;

    @Override
    @Transactional
    public Long enroll(long courseId, long studentId)
    {
        Enrollment enroll = Enrollment.enroll(courseId, studentId, enrollmentCreateValidator);
        Enrollment save = enrollmentRepository.save(enroll);

        Long enrollId = save.getId();
        publisher.publishEvent(
                EnrollRegisteredEvent.builder()
                        .courseId(courseId)
                        .studentId(studentId)
                        .build()
        );
        return enrollId;
    }

}
