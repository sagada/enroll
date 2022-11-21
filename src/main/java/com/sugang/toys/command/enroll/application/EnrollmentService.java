package com.sugang.toys.command.enroll.application;

import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.enroll.domain.Enrollment;
import com.sugang.toys.command.enroll.domain.EnrollmentRepository;
import com.sugang.toys.command.student.domain.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentService {

    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentCreateValidate enrollmentCreateValidate;

    @Autowired
    public EnrollmentService(
            EnrollmentRepository enrollmentRepository
            , EnrollmentCreateValidate enrollmentCreateValidate)
    {
        this.enrollmentRepository = enrollmentRepository;
        this.enrollmentCreateValidate = enrollmentCreateValidate;
    }

    public Long enroll(long courseId, long studentId)
    {
        Enrollment enroll = Enrollment.enroll(courseId, studentId, enrollmentCreateValidate);
        Enrollment save = enrollmentRepository.save(enroll);
        return save.getId();
    }

}
