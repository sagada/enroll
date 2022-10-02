package com.sugang.toys.command.student.application;

import com.sugang.toys.command.student.controller.StudentEnterCommand;
import com.sugang.toys.command.student.domain.Student;
import com.sugang.toys.command.student.domain.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class StudentEnterService {
    private final StudentRepository studentRepository;

    @Autowired
    public StudentEnterService(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }

    @Transactional
    public Student enter(StudentEnterCommand command)
    {
        Student student = Student.enter(
                command.getName()
                , null
                , command.getBirthDay());
        return studentRepository.save(student);
    }
}
