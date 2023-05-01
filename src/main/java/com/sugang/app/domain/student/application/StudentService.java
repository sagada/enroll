package com.sugang.app.domain.student.application;

import com.sugang.app.domain.student.domain.Student;

public interface StudentService {
    Student save(CreateStudentDto createStudentDto);
    boolean registerSemester(Student student);
}
