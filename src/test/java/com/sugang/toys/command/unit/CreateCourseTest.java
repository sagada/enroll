package com.sugang.toys.command.unit;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.sugang.toys.command.common.exception.ErrorCode;
import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.CourseSchedule;
import com.sugang.toys.command.course.domain.CourseSummary;
import com.sugang.toys.command.course.domain.exception.CourseException;
import com.sugang.toys.command.course.domain.service.CourseScheduleOverlapCheckService;
import com.sugang.toys.command.course.infra.CreateCourseValidatorImpl;
import com.sugang.toys.command.professor.domain.Professor;
import com.sugang.toys.command.professor.domain.ProfessorService;
import com.sugang.toys.command.subject.domain.Subject;
import com.sugang.toys.command.subject.domain.SubjectService;
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

import static com.sugang.toys.command.common.exception.ErrorCode.DUPLICATE_COURSE_NAME;

@ExtendWith(MockitoExtension.class)
public class CreateCourseTest {

    @InjectMocks
    CreateCourseValidatorImpl createCourseValidator;

    @Mock
    CourseRepository courseRepository;

    @Mock
    ProfessorService professorService;

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

    @DisplayName("????????? ?????? ???????????? ????????? ??????")
    @Test
    void duplicateCourseNameValidatorTest()
    {
        // given
        Mockito.when(courseRepository.existsByName(Mockito.any())).thenReturn("dddd");

        // when
        CourseException courseException = Assertions.assertThrows(CourseException.class,
                () -> Course.createCourse(
                        givenCourseSchedules()
                        , Set.of(new CourseSummary(1, "content", "title"))
                        , 1L
                        , 1L
                        , "course"
                        , "courseName"
                        , 10
                        , createCourseValidator
                )
        );

        // then
        Assertions.assertEquals(DUPLICATE_COURSE_NAME.getMessage(), courseException.getMessage());
    }

    @DisplayName("?????? ????????? working ?????? ?????? Error")
    @Test
    void professorStatusValidatorTest()
    {
        // given
        Professor professor = Mockito.mock(Professor.class);
        Mockito.when(professor.working()).thenReturn(false);
        Mockito.when(professorService.findById(Mockito.any())).thenReturn(professor);

        // when
        CourseException courseException = Assertions.assertThrows(CourseException.class,
                () -> Course.createCourse(
                        givenCourseSchedules()
                        , Set.of(new CourseSummary(1, "content", "title"))
                        , 1L
                        , 1L
                        , "course"
                        , "courseName"
                        , 10
                        , createCourseValidator
                )
        );

        // then
        Assertions.assertEquals(ErrorCode.PROFESSOR_STATUS.getMessage(), courseException.getMessage());
    }


    @DisplayName("????????? ?????? ????????? ???????????? ??????")
    @Test
    void duplicateSubjectValidatorTest()
    {
        // given
        Subject subject = Mockito.mock(Subject.class);
        Course course = Mockito.mock(Course.class);
        List<Course> courseList = Lists.newArrayList(course);

        Mockito.when(course.getSubject()).thenReturn(subject);
        Mockito.when(subjectService.findById(Mockito.any())).thenReturn(subject);
        Mockito.when(courseRepository.findByProfessorId(Mockito.any())).thenReturn(courseList);

        Professor professor = Mockito.mock(Professor.class);
        Mockito.when(professorService.findById(Mockito.any())).thenReturn(professor);
        Mockito.when(professor.working()).thenReturn(true);

        // when
        CourseException courseException = Assertions.assertThrows(CourseException.class,
                () -> Course.createCourse(
                        givenCourseSchedules()
                        , Set.of(new CourseSummary(1, "content", "title"))
                        , 1L
                        , 1L
                        , "course"
                        , "courseName"
                        , 10
                        , createCourseValidator
                )
        );

        // then
        Assertions.assertEquals(ErrorCode.DUPLICATE_COURSE.getMessage(), courseException.getMessage());
    }
}
