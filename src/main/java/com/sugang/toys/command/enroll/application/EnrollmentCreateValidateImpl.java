package com.sugang.toys.command.enroll.application;

import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.course.domain.CourseSchedule;
import com.sugang.toys.command.enroll.domain.Enrollment;
import com.sugang.toys.command.enroll.domain.EnrollmentRepository;
import com.sugang.toys.command.enroll.domain.EnrolmentStatus;
import com.sugang.toys.command.student.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class EnrollmentCreateValidateImpl implements EnrollmentCreateValidate{

    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentCreateValidateImpl(
            EnrollmentRepository enrollmentRepository)
    {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public void validate(Course course, Student student)
    {
        if (course.isClosed())
        {
            throw new RuntimeException("강좌 닫힘");
        }

        List<Enrollment> enrollmentListByStudentId = enrollmentRepository.findEnrollmentListByStudentId(student.getId());

        Set<CourseSchedule> courseSchedules = course.getCourseSchedules().courseScheduleSet();

        Set<CourseSchedule> semesterStudentCourseScheduleSets = enrollmentListByStudentId.stream()
                .filter(Predicate.not(enrollment -> enrollment.getEnrolmentStatus().equals(EnrolmentStatus.END)))
                .map(Enrollment::getCourse)
                .flatMap(studentCourse -> studentCourse.getCourseSchedules().courseScheduleSet().stream())
                .collect(Collectors.toSet());

        if (semesterStudentCourseScheduleSets
                .stream()
                .anyMatch(courseSchedule -> courseSchedule.contain(courseSchedules)))
        {
            throw new RuntimeException("겹치는 수업이 있습니다.");
        }
    }
}
