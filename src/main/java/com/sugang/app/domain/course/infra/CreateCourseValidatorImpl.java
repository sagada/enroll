package com.sugang.app.domain.course.infra;

import com.sugang.app.domain.professor.ProfessorRepository;
import com.sugang.app.global.common.exception.ErrorCode;
import com.sugang.app.domain.course.validator.CreateCourseValidator;
import com.sugang.app.domain.course.exception.CourseException;
import com.sugang.app.domain.course.service.CourseScheduleOverlapCheckService;
import com.sugang.app.domain.professor.Professor;
import com.sugang.app.domain.subject.Subject;
import com.sugang.app.domain.subject.SubjectService;
import com.sugang.app.domain.course.Course;
import com.sugang.app.domain.course.CourseName;
import com.sugang.app.domain.course.CourseRepository;
import com.sugang.app.domain.course.CourseSchedule;
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
    private final ProfessorRepository professorRepository;
    private final SubjectService subjectService;
    private final CourseScheduleOverlapCheckService courseScheduleOverlapCheckService;

    public void validate(Course course)
    {
        checkDuplicateCourseName(course.getCourseName());
        checkProfessorStatus(course.getProfessorId());

        Subject subject = subjectService.findById(course.getSubjectId());
        List<Course> professorCourseList = courseRepository.findByProfessorId(course.getProfessorId());

        checkDuplicateCourseSubject(subject, professorCourseList);
        checkOverlapCourseSchedule(course.getCourseSchedules().getCourseScheduleSet(), professorCourseList);
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
        Set<Long> professorSubjectIdSet = professorCourseList.stream()
                .map(Course::getSubjectId)
                .collect(Collectors.toSet());

        List<Subject> professorSubjectSet = subjectService.findByIds(professorSubjectIdSet);

        if (professorSubjectSet.contains(subject))
        {
            throw new IllegalArgumentException("시간표가 중복됩니다.");
        }
    }

    private void checkProfessorStatus(Long professorId)
    {
        Professor professor = professorRepository.findById(professorId).orElseThrow();

        if (!professor.working())
        {
            throw new CourseException(ErrorCode.PROFESSOR_STATUS);
        }
    }

    private void checkDuplicateCourseName(CourseName courseName)
    {
        if (courseRepository.existsByCourseName(courseName))
        {
            throw new CourseException(ErrorCode.DUPLICATE_COURSE_NAME);
        }
    }
}
