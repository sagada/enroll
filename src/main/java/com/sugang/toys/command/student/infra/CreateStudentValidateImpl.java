package com.sugang.toys.command.student.infra;

import com.sugang.toys.command.department.domain.DepartmentService;
import com.sugang.toys.command.student.domain.Student;
import com.sugang.toys.command.student.domain.StudentRepository;
import com.sugang.toys.command.student.domain.CreateStudentValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateStudentValidateImpl implements CreateStudentValidate {

    private final StudentRepository studentRepository;
    private final DepartmentService departmentService;

    @Autowired
    public CreateStudentValidateImpl(
            StudentRepository studentRepository
            , DepartmentService departmentService)
    {
        this.studentRepository = studentRepository;
        this.departmentService = departmentService;
    }

    @Override
    public void validate(Student student)
    {
        validate(student, student.getAdvisorProfessorId(), student.getDepartmentId());
    }

    private void validate(Student student, Long advisorProfessorId, Long departmentId)
    {

    }
}
