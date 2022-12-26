package com.sugang.toys.command.course.infra;

import com.sugang.toys.command.common.exception.ErrorCode;
import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.CourseSchedule;
import com.sugang.toys.command.course.domain.CreateCourseValidator;
import com.sugang.toys.command.course.domain.exception.CourseException;
import com.sugang.toys.command.course.domain.service.CourseScheduleOverlapCheckService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

/**
 * course 생성 validator
 * 1. 교수 수업하고 추가된 과목의 수업 시간 겹치는 거
 * 2. 수업 이름 중복인지 확인
 */
@RequiredArgsConstructor
@Service
public class CreateCourseValidatorImpl implements CreateCourseValidator {

    private final CourseRepository courseRepository;
    private final CourseScheduleOverlapCheckService courseScheduleOverlapCheckService;

    public void validate(
            Long professorId
            , String courseName
            , Set<CourseSchedule> openCourseScheduleSet)
    {
        duplicateCourseNameCheck(courseName);

        Set<CourseSchedule> professorCourseSchedules = courseRepository.findProfessorCourseSchedules(professorId)
                .stream()
                .flatMap(courseSchedules -> courseSchedules.getCourseScheduleSet().stream())
                .collect(Collectors.toSet());

        courseScheduleOverlapCheckService.isOverlap(professorCourseSchedules, openCourseScheduleSet);
    }

    private void duplicateCourseNameCheck(String courseName)
    {
        if (courseRepository.existsByName(courseName))
        {
            throw new CourseException(ErrorCode.DUPLICATE_COURSE_NAME);
        }
    }


}
