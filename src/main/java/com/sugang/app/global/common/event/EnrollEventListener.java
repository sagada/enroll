package com.sugang.app.global.common.event;

import com.sugang.app.global.config.kafka.EnrollmentLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

@Slf4j
@Service
public class EnrollEventListener {

    private final KafkaTemplate<Long, EnrollmentLog> enrollmentLogKafkaTemplate;
    private static final String ENROLL_LOGGING_TOPIC_NAME = "enroll-log";

    @Autowired
    public EnrollEventListener(
           @Qualifier("enrollmentLogKafkaTemplate") KafkaTemplate<Long, EnrollmentLog> enrollmentLogKafkaTemplate)
    {
        this.enrollmentLogKafkaTemplate = enrollmentLogKafkaTemplate;
    }

    @TransactionalEventListener
    public void listener(final EnrollRegisteredEvent event)
    {
        EnrollmentLog enrollmentLog = new EnrollmentLog();

        enrollmentLog.setEnrollmentId(event.getEnrollmentId());
        enrollmentLog.setScore(event.getCourseScore());
        enrollmentLog.setCourseId(event.getCourseId());
        enrollmentLog.setCourseName(event.getCourseName());
        enrollmentLog.setStudentId(event.getStudentId());
        enrollmentLog.setStudentName(event.getStudentName());
        enrollmentLog.setStudentId(event.getProfessorId());

        enrollmentLogKafkaTemplate.send(ENROLL_LOGGING_TOPIC_NAME, enrollmentLog);
    }
}
