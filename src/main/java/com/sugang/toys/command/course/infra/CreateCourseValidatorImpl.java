package com.sugang.toys.command.course.infra;

import com.sugang.toys.command.common.exception.ErrorCode;
import com.sugang.toys.command.course.domain.*;
import com.sugang.toys.command.course.domain.CreateCourseValidator;
import com.sugang.toys.command.course.domain.exception.CourseException;
import com.sugang.toys.command.course.domain.service.CourseScheduleOverlapCheckService;
import com.sugang.toys.command.professor.domain.Professor;
import com.sugang.toys.command.professor.domain.ProfessorService;
import com.sugang.toys.command.subject.domain.Subject;
import com.sugang.toys.command.subject.domain.SubjectService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * course 생성 validator
 * 1. 수업 이름 중복인지 확인
 * 2. 교수 상태 확인
 * 3. 교수 중복 과목 확인
 * 4. 교수 기존 수업 스케줄 중복 확인
 */
@RequiredArgsConstructor
@Service
public class CreateCourseValidatorImpl implements CreateCourseValidator {

    private final CourseRepository courseRepository;
    private final ProfessorService professorService;
    private final SubjectService subjectService;
    private final CourseScheduleOverlapCheckService courseScheduleOverlapCheckService;

    public void validate(
            Long subjectId
            , Long professorId
            , Set<Long> preCourseIdSet
            , CourseName courseName
            , Set<CourseSchedule> openCourseScheduleSet)
    {
        checkDuplicateCourseName(courseName);
        checkProfessorStatus(professorId);

        Subject subject = subjectService.findById(subjectId);
        List<Course> professorCourseList = courseRepository.findByProfessorId(professorId);

        checkDuplicateCourseSubject(subject, professorCourseList);
        checkOverlapCourseSchedule(openCourseScheduleSet, professorCourseList);
    }

    private void checkOverlapCourseSchedule(
            Set<CourseSchedule> openCourseScheduleSet
            , List<Course> professorCourseList)
    {
        Set<CourseSchedule> professorCourseSchedules = professorCourseList
                .stream()
                .map(Course::getCourseSchedules)
                .flatMap(courseSchedules -> courseSchedules.getCourseScheduleSet().stream())
                .collect(Collectors.toSet());

        courseScheduleOverlapCheckService.isOverlap(professorCourseSchedules, openCourseScheduleSet);
    }

    private void checkDuplicateCourseSubject(Subject subject, List<Course> professorCourseList)
    {
        Set<Subject> professorSubjectSet = professorCourseList.stream()
                .map(Course::getSubject)
                .collect(Collectors.toSet());

        if (professorSubjectSet.contains(subject))
        {
            throw new CourseException(ErrorCode.DUPLICATE_COURSE);
        }
    }

    private void checkProfessorStatus(Long professorId)
    {
        Professor professor = professorService.findById(professorId);

        if (!professor.working())
        {
            throw new CourseException(ErrorCode.PROFESSOR_STATUS);
        }
    }

    private void checkDuplicateCourseName(CourseName courseName)
    {
        if (courseRepository.existsByName(courseName))
        {
            throw new CourseException(ErrorCode.DUPLICATE_COURSE_NAME);
        }
    }
}
