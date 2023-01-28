package com.sugang.toys.command.student.application;

import com.sugang.toys.command.student.domain.Student;

public interface StudentService {
    Student save(CreateStudentDto createStudentDto);
    boolean registerSemester(Student student);
}
