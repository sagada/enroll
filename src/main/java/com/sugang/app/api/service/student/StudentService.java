package com.sugang.app.api.service.student;

import com.sugang.app.api.service.student.request.CreateStudentServiceDto;
import com.sugang.app.domain.student.Student;

public interface StudentService {
    Student save(CreateStudentServiceDto createStudentDto);
}
