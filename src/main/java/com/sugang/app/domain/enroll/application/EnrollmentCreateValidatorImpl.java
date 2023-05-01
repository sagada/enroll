package com.sugang.app.domain.enroll.application;

import com.sugang.app.domain.course.domain.Course;
import com.sugang.app.domain.course.domain.CourseRepository;
import com.sugang.app.domain.enroll.domain.EnrollmentRepository;
import com.sugang.app.domain.student.domain.Student;
import com.sugang.app.domain.student.domain.StudentRepository;
import com.sugang.app.global.exception.DomainException;
import com.sugang.app.global.config.db.ErrorCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class EnrollmentCreateValidatorImpl implements EnrollmentCreateValidator {

    private final EnrollmentRepository enrollmentRepository;
    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public EnrollmentCreateValidatorImpl(
            EnrollmentRepository enrollmentRepository
            , StudentRepository studentRepository
            , CourseRepository courseRepository)
    {
        this.enrollmentRepository = enrollmentRepository;
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    public Student getStudent(Long studentId)
    {
        return studentRepository.findById(studentId)
                .orElseThrow(()-> new DomainException(ErrorCode.STUDENT_NOT_FOUND));
    }

    public void validate(Course course, Student student)
    {
        if (course.isClosed())
        {
            throw new RuntimeException("Closed Course");
        }
    }

    @Override
    public void validate(Long courseId, Long studentId)
    {
        validate(getCourse(courseId), getStudent(studentId));
    }

    private Course getCourse(Long courseId)
    {
        return courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("없는 수업입니다."));
    }
}
