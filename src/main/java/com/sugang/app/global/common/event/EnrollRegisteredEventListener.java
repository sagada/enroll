package com.sugang.app.global.common.event;

import com.sugang.app.domain.course.Course;
import com.sugang.app.domain.course.CourseRepository;
import com.sugang.app.domain.professor.Professor;
import com.sugang.app.domain.professor.ProfessorRepository;
import com.sugang.app.domain.student.Student;
import com.sugang.app.domain.student.StudentRepository;
import com.sugang.app.global.config.kafka.EnrollmentLog;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionalEventListener;

import javax.transaction.Transactional;

@Slf4j
@Transactional
@Service
public class EnrollRegisteredEventListener {

    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;

    private final KafkaTemplate<Long, EnrollmentLog> enrollmentLogKafkaTemplate;
    private static final String ENROLL_LOGGING_TOPIC_NAME = "enroll-log";

    @Autowired
    public EnrollRegisteredEventListener(
            CourseRepository courseRepository
            , StudentRepository studentRepository
            , ProfessorRepository professorRepository
            , @Qualifier("enrollmentLogKafkaTemplate") KafkaTemplate<Long, EnrollmentLog> enrollmentLogKafkaTemplate
    )
    {
        this.courseRepository = courseRepository;
        this.studentRepository = studentRepository;
        this.professorRepository = professorRepository;
        this.enrollmentLogKafkaTemplate = enrollmentLogKafkaTemplate;
    }

    @TransactionalEventListener
    public void listener(final EnrollRegisteredEvent event)
    {
        log.info("enroll logging EnrollId : {}, StudentId : {}", event.getEnrollId(), event.getStudentId());

        EnrollmentLog enrollmentLog = new EnrollmentLog();

        Student student = studentRepository.findById(event.getStudentId()).orElseThrow();
        Course course = courseRepository.findById(event.getCourseId()).orElseThrow();
        Professor professor = professorRepository.findById(course.getProfessorId()).orElseThrow();

        enrollmentLog.setCourseId(event.getCourseId());
        enrollmentLog.setEnrollmentId(event.getEnrollId());
        enrollmentLog.setStudentId(event.getStudentId());
        enrollmentLog.setStudentName(student.getName());
        enrollmentLog.setCourseName(course.getCourseName().getValue());
        enrollmentLog.setCourseId(course.getId());
        enrollmentLog.setProfessorId(professor.getId());
        enrollmentLog.setProfessorName(professor.getName());
        enrollmentLog.setScore(course.getScore());

        enrollmentLogKafkaTemplate.send(ENROLL_LOGGING_TOPIC_NAME, enrollmentLog);
    }
}
