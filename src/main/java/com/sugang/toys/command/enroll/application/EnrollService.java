package com.sugang.toys.command.enroll.application;

import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.enroll.domain.Enrollment;
import com.sugang.toys.command.enroll.domain.EnrollmentRepository;
import com.sugang.toys.command.student.domain.Student;
import com.sugang.toys.command.student.domain.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollService {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentCreateValidate enrollmentCreateValidate;

    @Autowired
    public EnrollService(
            EnrollmentRepository enrollmentRepository
            , StudentRepository studentRepository
            , CourseRepository courseRepository
            , EnrollmentCreateValidate enrollmentCreateValidate)
    {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentCreateValidate = enrollmentCreateValidate;
    }

    public Long enroll(long courseId, long studentId)
    {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new EnrollmentException("없는 수강"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("없는 학생"));

        Enrollment enroll = Enrollment.enroll(course, student, enrollmentCreateValidate);

        Enrollment save = enrollmentRepository.save(enroll);
        return save.getId();
    }

}
