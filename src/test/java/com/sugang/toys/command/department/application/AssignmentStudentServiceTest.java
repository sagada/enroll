package com.sugang.toys.command.department.application;

import com.sugang.toys.command.department.domain.Department;
import com.sugang.toys.command.department.domain.DepartmentPhoneNumber;
import com.sugang.toys.command.department.domain.DepartmentRepository;
import com.sugang.toys.command.department.domain.Division;
import com.sugang.toys.command.student.domain.Student;
import com.sugang.toys.command.student.domain.StudentRepository;
import com.sugang.toys.command.student.domain.StudentStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AssignmentStudentServiceTest {

    @InjectMocks
    AssignmentStudentService assignmentStudentService;

    @Mock
    StudentRepository studentRepository;

    @Mock
    DepartmentRepository departmentRepository;

    @Test
    void 학과_변경_테스트()
    {
        Student student = Student.create(1L, "ysh", null, "2022-02-23", StudentStatus.ENTER, 1);
        // given
        Mockito.when(studentRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(student));

        Department department = Department.create(
                1L,
                "컴퓨터공학과",
                Division.IT,
                new DepartmentPhoneNumber("123-233-3232", "2323-2332")
        );

        Mockito.when(departmentRepository.findById(Mockito.any()))
                .thenReturn(Optional.of(department));

        // when
        assignmentStudentService.assign(1L, 1L);

        // then
        Assertions.assertEquals(student.getDepartment().getName(), "컴퓨터공학과");
    }

}