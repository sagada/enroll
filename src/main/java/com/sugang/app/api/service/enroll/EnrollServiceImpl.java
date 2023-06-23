package com.sugang.app.api.service.enroll;

import com.sugang.app.api.service.enroll.response.EnrollServiceResponse;
import com.sugang.app.domain.course.Course;
import com.sugang.app.domain.course.CourseRepository;
import com.sugang.app.domain.enroll.Enrollment;
import com.sugang.app.domain.enroll.EnrollmentRepository;
import com.sugang.app.domain.student.Student;
import com.sugang.app.domain.student.StudentRepository;
import com.sugang.app.global.common.event.EnrollRegisteredEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@RequiredArgsConstructor
@Service
@Transactional
public class EnrollServiceImpl implements EnrollService {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final StudentRepository studentRepository;
    private final EnrollValidator enrollValidator;

    private final ApplicationEventPublisher publisher;

    @Override
    public EnrollServiceResponse enroll(long courseId, long studentId)
    {
        Course course = courseRepository.findCourseByIdWithLock(courseId);
        Student student = studentRepository.findStudentByIdWithLock(studentId);

        enrollValidator.validate(course, student);

        course.addStudent();
        student.decreaseScore(course.getScore());

        Enrollment enroll = Enrollment.enroll(courseId, studentId);
        Enrollment save = enrollmentRepository.save(enroll);

        Long enrollId = save.getId();

        publisher.publishEvent(
                EnrollRegisteredEvent.builder()
                        .enrollmentId(enrollId)
                        .studentName(student.getName())
                        .studentId(studentId)
                        .courseName(course.getCourseName().getValue())
                        .professorId(course.getProfessorId())
                        .courseScore(course.getScore())
                        .build()
        );

        return EnrollServiceResponse.builder()
                .registerDateTime(save.getCreatedDateTime())
                .enrollId(enrollId)
                .studentName(student.getName())
                .courseId(courseId)
                .courseName(course.getCourseName().getValue())
                .studentId(studentId)
                .build();
    }
}
