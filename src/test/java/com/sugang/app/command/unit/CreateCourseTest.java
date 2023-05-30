package com.sugang.app.command.unit;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sugang.app.domain.professor.domain.ProfessorRepository;
import com.sugang.app.global.common.exception.ErrorCode;
import com.sugang.app.domain.course.domain.*;
import com.sugang.app.domain.course.domain.exception.CourseException;
import com.sugang.app.domain.course.domain.service.CourseScheduleOverlapCheckService;
import com.sugang.app.domain.course.infra.CreateCourseValidatorImpl;
import com.sugang.app.domain.professor.domain.Professor;
import com.sugang.app.domain.subject.domain.Subject;
import com.sugang.app.domain.subject.domain.SubjectService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.*;
import java.util.List;
import java.util.Set;

import static com.sugang.app.global.common.exception.ErrorCode.DUPLICATE_COURSE_NAME;

@ExtendWith(MockitoExtension.class)
public class CreateCourseTest {

    @InjectMocks
    CreateCourseValidatorImpl createCourseValidator;

    @Mock
    CourseRepository courseRepository;

    @Mock
    ProfessorRepository professorService;

    @Mock
    SubjectService subjectService;

    @Mock
    CourseScheduleOverlapCheckService courseScheduleOverlapCheckService;

    static Set<CourseSchedule> givenCourseSchedules()
    {
        CourseSchedule courseSchedule1 = new CourseSchedule(
                LocalDateTime.of(LocalDate.of(2022, Month.MARCH, 23), LocalTime.of(11, 20)),
                LocalDateTime.of(LocalDate.of(2022, Month.MARCH, 23), LocalTime.of(20, 30)),
                "4567"
        );

        CourseSchedule courseSchedule2 = new CourseSchedule(
                LocalDateTime.of(LocalDate.of(2022, Month.MARCH, 24), LocalTime.of(11, 20)),
                LocalDateTime.of(LocalDate.of(2022, Month.MARCH, 24), LocalTime.of(20, 30)),
                "1234"
        );

        return Sets.newHashSet(courseSchedule1, courseSchedule2);
    }

    @DisplayName("중복된 수업 이름으로 생성시 에러")
    @Test
    void duplicateCourseNameValidatorTest()
    {
        // given
        Mockito.when(courseRepository.existsByCourseName(Mockito.any())).thenReturn(true);

        // when
        CourseException courseException = Assertions.assertThrows(CourseException.class,
                () -> Course.createCourse(
                        givenCourseSchedules()
                        , Set.of(new CourseSummary(1, "content", "title"))
                        , new CourseExamination(LocalDateTime.now(), LocalDateTime.now())
                        , Set.of(1L, 2L)
                        , 1L
                        , 1L
                        , new CourseName("course")
                        , 10
                        , createCourseValidator
                )
        );

        // then
        Assertions.assertEquals(DUPLICATE_COURSE_NAME.getMessage(), courseException.getMessage());
    }

    @DisplayName("교수 상태가 working 아닐 경우 Error")
    @Test
    void professorStatusValidatorTest()
    {
        // given
        Professor professor = Mockito.mock(Professor.class);
        Mockito.when(professor.working()).thenReturn(false);
        Mockito.when(professorService.findById(Mockito.any()).orElseThrow()).thenReturn(professor);

        // when
        CourseException courseException = Assertions.assertThrows(CourseException.class,
                () -> Course.createCourse(
                        givenCourseSchedules()
                        , Set.of(new CourseSummary(1, "content", "title"))
                        , new CourseExamination()
                        , Set.of(1L, 2L)
                        , 1L
                        , 1L
                        , new CourseName("course")
                        , 10
                        , createCourseValidator
                )
        );

        // then
        Assertions.assertEquals(ErrorCode.PROFESSOR_STATUS.getMessage(), courseException.getMessage());
    }


    @DisplayName("중복된 과목 수업을 가졌으면 에러")
    @Test
    void duplicateSubjectValidatorTest()
    {
        // given
        Subject subject = Mockito.mock(Subject.class);
        Course course = Mockito.mock(Course.class);
        List<Course> courseList = Lists.newArrayList(course);

        Mockito.when(subjectService.findById(Mockito.any())).thenReturn(subject);
        Mockito.when(courseRepository.findByProfessorId(Mockito.any())).thenReturn(courseList);

        Professor professor = Mockito.mock(Professor.class);
        Mockito.when(professorService.findById(Mockito.any()).orElseThrow()).thenReturn(professor);
        Mockito.when(professor.working()).thenReturn(true);

        // when
        CourseException courseException = Assertions.assertThrows(CourseException.class,
                () -> Course.createCourse(
                        givenCourseSchedules()
                        , Set.of(new CourseSummary(1, "content", "title"))
                        , new CourseExamination()
                        , Set.of(1L,2L)
                        , 1L
                        , 1L
                        , new CourseName("course")
                        , 10
                        , createCourseValidator
                )
        );

        // then
        Assertions.assertEquals(ErrorCode.DUPLICATE_COURSE.getMessage(), courseException.getMessage());
    }
}
