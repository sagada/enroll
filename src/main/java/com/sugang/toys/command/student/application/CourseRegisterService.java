package com.sugang.toys.command.student.application;

import com.sugang.toys.command.common.exception.ErrorCode;
import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.student.domain.Student;
import com.sugang.toys.command.student.domain.StudentRepository;
import com.sugang.toys.command.student.domain.exception.StudentException;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Log4j2
@Service
public class CourseRegisterService {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;

    @Autowired
    public CourseRegisterService(
            StudentRepository studentRepository
            , CourseRepository courseRepository)
    {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
    }

    @Transactional
    public void registerCourse(Long studentId, Long courseId)
    {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentException(ErrorCode.NONE_STUDENT));

        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException(ErrorCode.NONE_STUDENT.getMessage()));

        student.registerCourse(course);
    }
}
