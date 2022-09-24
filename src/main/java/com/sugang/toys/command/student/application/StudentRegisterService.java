package com.sugang.toys.command.student.application;

import com.sugang.toys.command.student.domain.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentRegisterService {

    private final StudentRepository studentRepository;

    @Autowired
    public StudentRegisterService(StudentRepository studentRepository)
    {
        this.studentRepository = studentRepository;
    }

    public void register()
    {

    }
}
