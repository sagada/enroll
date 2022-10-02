package com.sugang.toys.command.course.application;

import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.department.domain.Department;
import com.sugang.toys.command.department.domain.DepartmentRepository;
import com.sugang.toys.command.department.domain.Division;
import com.sugang.toys.command.professor.domain.Professor;
import com.sugang.toys.command.professor.domain.ProfessorRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.test.context.jdbc.Sql;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Optional;
import java.util.Set;

@Sql
@ExtendWith(MockitoExtension.class)
public class CourseAddServiceTest {

    @InjectMocks
    CourseAddService courseAddService;

    @Mock
    CourseRepository courseRepository;

    @Mock
    DepartmentRepository departmentRepository;

    @Mock
    ProfessorRepository professorRepository;

    @Test
    void addCourseTest()
    {
        // given
        Department department = new Department(
                99999L
                , "name"
                , Division.IT
                , "1232-1232"
                , "1232-1232");

        Professor professor = new Professor(19999L, "name");

        Mockito.when(departmentRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(department));

        Mockito.when(professorRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(professor));

        AddCourseRequest addCourseRequest = new AddCourseRequest();
        addCourseRequest.setCourseName("course_name");
        addCourseRequest.setProfessorId(19999L);
        addCourseRequest.setDepartmentId(99999L);
        addCourseRequest.setMaxCourseStudentCount(10);
        addCourseRequest.setCourseScheduleSet(Set.of(new AddCourseRequest.CourseScheduleDto(
                DayOfWeek.FRIDAY,
                LocalDateTime.of(2022, Month.DECEMBER, 11, 22, 3),
                LocalDateTime.of(2022, Month.DECEMBER, 11, 22, 3),
                "1234"
        )));

        // when
        courseAddService.addCourse(addCourseRequest);

        Mockito.verify(courseRepository, Mockito.times(1))
                .save(Mockito.any());
    }
}
