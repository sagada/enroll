package com.sugang.app.api.service.enroll;

import com.sugang.app.api.service.course.CourseScheduleOverlapCheckService;
import com.sugang.app.domain.course.Course;
import com.sugang.app.domain.course.CourseRepository;
import com.sugang.app.domain.course.CourseSchedule;
import com.sugang.app.domain.enroll.Enrollment;
import com.sugang.app.domain.enroll.EnrollmentRepository;
import com.sugang.app.domain.student.Student;
import com.sugang.app.global.exception.ApiException;
import com.sugang.app.global.exception.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * 1. 닫힌 수강
 * 2. 학생 이수 학점 초과
 * 3. 수업 수강 가능 인원 부족
 * 4. 이미 등록한 수업
 * 5. 등록한 수업과의 시간 겹치는지 확인
 */
@RequiredArgsConstructor
@Service
public class EnrollValidatorImpl implements EnrollValidator {

    private final EnrollmentRepository enrollmentRepository;
    private final CourseRepository courseRepository;
    private final CourseScheduleOverlapCheckService courseScheduleOverlapCheckService;

    @Override
    public void validate(Course course, Student student)
    {
        if (course.isClosed())
        {
            throw new ApiException(ErrorCode.CLOSED_COURSE);
        }

        if (student.getAvailableScore() - course.getScore() < 0)
        {
            throw new ApiException(ErrorCode.EXCEEDED_COURSE_CREDIT);
        }

        if (!course.availableAddStudent())
        {
            throw new ApiException(ErrorCode.EXCEEDED_COURSE_STUDENT);
        }

        List<Enrollment> studentCourseIdList = enrollmentRepository.findByStudentId(student.getId());

        if (studentCourseIdList.stream().map(Enrollment::getCourseId).collect(Collectors.toSet())
                .contains(course.getId()))
        {
            throw new ApiException(ErrorCode.DUPLICATE_COURSE);
        }

        Set<Long> studentCourseIdSet = studentCourseIdList.stream()
                .map(Enrollment::getCourseId)
                .collect(Collectors.toSet());

        List<Course> studentCourses = courseRepository.findAllById(studentCourseIdSet);

        Set<CourseSchedule> courseScheduleList = studentCourses.stream()
                .flatMap(studentCourse -> studentCourse.getCourseScheduleSet().stream())
                .collect(Collectors.toSet());

        if (courseScheduleOverlapCheckService.isOverlap(courseScheduleList, course.getCourseScheduleSet()))
        {
            throw new ApiException(ErrorCode.DUPLICATE_COURSE_TIME);
        }
    }
}
