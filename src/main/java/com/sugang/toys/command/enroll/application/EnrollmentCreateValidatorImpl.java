package com.sugang.toys.command.enroll.application;

import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.CourseSchedule;
import com.sugang.toys.command.enroll.domain.EnrollmentRepository;
import com.sugang.toys.command.student.domain.Student;
import com.sugang.toys.command.student.domain.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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
                .orElseThrow(()-> new RuntimeException("없는 학생 ID"));
    }

    public void validate(Course course, Student student)
    {
        if (course.isClosed())
        {
            throw new RuntimeException("Closed Course");
        }

        // TODO : 조회 전용 쿼리 추가후 수정
        List<Long> studentCourseIdList = enrollmentRepository.findEnrollmentListByStudentId(student.getId());
        Set<CourseSchedule> courseSchedules = course.getCourseSchedules().getCourseScheduleSet();
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
