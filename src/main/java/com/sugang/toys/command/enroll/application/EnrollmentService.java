package com.sugang.toys.command.enroll.application;

import com.sugang.toys.command.common.event.EnrollRegisteredEvent;
import com.sugang.toys.command.enroll.domain.Enrollment;
import com.sugang.toys.command.enroll.domain.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentCreateValidate enrollmentCreateValidate;
    private final ApplicationEventPublisher publisher;

    @Autowired
    public EnrollmentService(
            EnrollmentRepository enrollmentRepository
            , EnrollmentCreateValidate enrollmentCreateValidate
            , ApplicationEventPublisher publisher)
    {
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentCreateValidate = enrollmentCreateValidate;
        this.publisher = publisher;
    }

    @Transactional
    public Long enroll(long courseId, long studentId)
    {
        Enrollment enroll = Enrollment.enroll(courseId, studentId, enrollmentCreateValidate);
        Enrollment save = enrollmentRepository.save(enroll);

        Long enrollId = save.getId();

        publisher.publishEvent(new EnrollRegisteredEvent(courseId, studentId, enrollId));
        return enrollId;
    }

}
