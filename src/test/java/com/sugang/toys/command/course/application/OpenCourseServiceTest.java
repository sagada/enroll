package com.sugang.toys.command.course.application;

import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.CourseSchedule;
import com.sugang.toys.command.department.domain.Department;
import com.sugang.toys.command.department.domain.DepartmentRepository;
import com.sugang.toys.command.department.domain.Division;
import com.sugang.toys.command.professor.domain.Professor;
import com.sugang.toys.command.professor.domain.ProfessorRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class OpenCourseServiceTest {

    @InjectMocks
    private OpenCourseService openCourseService;

    @Mock
    CourseRepository courseRepository;

    @Mock
    ProfessorRepository professorRepository;

    @Mock
    DepartmentRepository departmentRepository;

    @Test
    void 강좌_생성_테스트()
    {
        // given
        OpenCourseRequest openCourseRequest = new OpenCourseRequest()
                .setCourseName("course_name")
                .setProfessorId(1111L)
                .setDepartmentId(99999L)
                .setMaxCourseStudentCount(10)
                .setCourseScheduleSet(
                        Set.of(
                                new OpenCourseRequest.CourseScheduleDto(
                                        DayOfWeek.FRIDAY,
                                        LocalDateTime.of(2022, Month.DECEMBER, 11, 22, 3),
                                        LocalDateTime.of(2022, Month.DECEMBER, 11, 22, 3),
                                        "1234"
                                )));

        Mockito.when(professorRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(new Professor(1L, "프로페서")));

        Mockito.when(departmentRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(new Department(1L, "부서", Division.IT, "2022-02-02", "202-0222")));

        Course course = Course.create(
                1L,
                Set.of(new CourseSchedule(
                        DayOfWeek.FRIDAY,
                        LocalDateTime.of(2022, Month.DECEMBER, 11, 22, 3),
                        LocalDateTime.of(2022, Month.DECEMBER, 11, 22, 3)
                        , "1234"
                )),
                null,
                null,
                "자료구조",
                new Department(),
                10
        );

        Mockito.when(courseRepository.save(Mockito.any()))
                .thenReturn(course);

        // when
        Long id = openCourseService.addCourse(openCourseRequest);

        // then
        Assertions.assertEquals(id, 1L);
        Mockito.verify(courseRepository, Mockito.times(1)).save(Mockito.any());
        Mockito.verify(professorRepository, Mockito.times(1)).findById(Mockito.any());
        Mockito.verify(departmentRepository, Mockito.times(1)).findById(Mockito.any());
    }
}
