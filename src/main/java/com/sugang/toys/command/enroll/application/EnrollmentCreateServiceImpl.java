package com.sugang.toys.command.enroll.application;

import com.sugang.toys.command.common.event.EnrollRegisteredEvent;
import com.sugang.toys.command.enroll.domain.Enrollment;
import com.sugang.toys.command.enroll.domain.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
public class EnrollmentCreateServiceImpl implements EnrollmentCreateService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentCreateValidator enrollmentCreateValidator;
    private final ApplicationEventPublisher publisher;

    @Autowired
    public EnrollmentCreateServiceImpl(
            EnrollmentRepository enrollmentRepository
            , EnrollmentCreateValidator enrollmentCreateValidator
            , ApplicationEventPublisher publisher)
    {
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentCreateValidator = enrollmentCreateValidator;
        this.publisher = publisher;
    }

    @Override
    @Transactional
    public Long enroll(long courseId, long studentId)
    {
        Enrollment enroll = Enrollment.enroll(courseId, studentId, enrollmentCreateValidator);
        Enrollment save = enrollmentRepository.save(enroll);

        Long enrollId = save.getId();

        publisher.publishEvent(new EnrollRegisteredEvent(courseId, studentId, enrollId));
        return enrollId;
    }

}
