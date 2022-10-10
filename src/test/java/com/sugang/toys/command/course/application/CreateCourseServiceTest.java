package com.sugang.toys.command.course.application;

import com.sugang.toys.command.TestContainerConfiguration;
import com.sugang.toys.command.course.application.dto.CourseCreateCommand;
import com.sugang.toys.command.course.application.dto.CourseScheduleRequest;
import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.department.domain.Department;
import com.sugang.toys.command.department.domain.DepartmentRepository;
import com.sugang.toys.command.professor.domain.Professor;
import com.sugang.toys.command.professor.domain.ProfessorRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@ExtendWith(MockitoExtension.class)
public class CreateCourseServiceTest extends TestContainerConfiguration
{

    @Autowired
    ProfessorRepository professorRepository;

    @MockBean
    DepartmentRepository departmentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CreateCourseService createCourseService;

    @Test
    void createCourseTest()
    {
        // given
        Professor professor = new Professor(1L, "1234");
        Department department = new Department(1L, null, null, null, null);
        professorRepository.save(professor);

        BDDMockito.given(departmentRepository.findById(Mockito.any())).willReturn(Optional.of(department));

        CourseCreateCommand courseCreateCommand = new CourseCreateCommand()
                .setCourseName("create_number_one")
                .setCourseScheduleSet(
                        Set.of(
                            new CourseScheduleRequest(DayOfWeek.FRIDAY, LocalDateTime.now(), LocalDateTime.now(), "1234")
                        )
                )
                .setMaxCourseStudentCount(10)
                .setDepartmentId(1L)
                .setProfessorId(1L);

        // when
        CreatedCourseResult course = createCourseService.createCourse(courseCreateCommand);

        // then
        Assertions.assertThat(course).isNotNull();
        BDDMockito.then(departmentRepository).should(Mockito.times(1)).findById(1L);
        Assertions.assertThat(course.getProfessorId()).isEqualTo(1L);
        Assertions.assertThat(course.getProfessorName()).isEqualTo("1234");
        Assertions.assertThat(course.getCourseName()).isEqualTo("create_number_one");
        Assertions.assertThat(course.getCourseScheduleResultSet()).hasSize(1);
    }
}
