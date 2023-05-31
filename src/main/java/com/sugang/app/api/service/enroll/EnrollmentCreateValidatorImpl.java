package com.sugang.app.api.service.enroll;

import com.sugang.app.domain.course.Course;
import com.sugang.app.domain.course.CourseRepository;
import com.sugang.app.domain.student.Student;
import com.sugang.app.domain.student.StudentRepository;
import com.sugang.app.global.config.db.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class EnrollmentCreateValidatorImpl implements EnrollmentCreateValidator {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    public Student getStudent(Long studentId)
    {
        return studentRepository.findById(studentId)
                .orElseThrow(()-> new IllegalStateException(ErrorCode.STUDENT_NOT_FOUND.getMessage()));
    }

    public void validate(Course course, Student student)
    {
        if (course.isClosed())
        {
            throw new IllegalStateException("Closed Course!");
        }

        if (student.getAvailableScore() - course.getScore() < 0)
        {
            throw new IllegalStateException("student status fail!");
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
                .orElseThrow(() -> new IllegalStateException("empty course"));
    }
}
