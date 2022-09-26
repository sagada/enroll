package com.sugang.toys.command.department.application;

import com.sugang.toys.command.common.exception.ErrorCode;
import com.sugang.toys.command.department.domain.Department;
import com.sugang.toys.command.department.domain.DepartmentRepository;
import com.sugang.toys.command.student.domain.Student;
import com.sugang.toys.command.student.domain.StudentRepository;
import com.sugang.toys.command.student.domain.exception.StudentException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssignmentStudentService {
    private final DepartmentRepository departmentRepository;
    private final StudentRepository studentRepository;

    @Autowired
    public AssignmentStudentService(
            DepartmentRepository departmentRepository
            , StudentRepository studentRepository)
    {
        this.departmentRepository = departmentRepository;
        this.studentRepository = studentRepository;
    }

    public void assign(Long studentId, Long departMentId)
    {
        Department department = departmentRepository.findById(departMentId)
                .orElseThrow(() -> new RuntimeException("없는 학과"));

        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new StudentException(ErrorCode.NONE_STUDENT));

        student.assignDepartment(department);
    }
}
