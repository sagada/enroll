package com.sugang.app.api.service.student;

import com.sugang.app.api.service.student.request.CreateStudentServiceDto;
import com.sugang.app.domain.student.Student;
import com.sugang.app.domain.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Transactional
@RequiredArgsConstructor
@Service
public class StudentService {

    private final StudentRepository studentRepository;

    public Student save(CreateStudentServiceDto createStudentDto)
    {
        Student student = Student.createStudent(
                createStudentDto.name(),
                createStudentDto.email(),
                createStudentDto.academicYear(),
                createStudentDto.score(),
                createStudentDto.advisorProfessorId(),
                createStudentDto.departmentId()
        );

        return studentRepository.save(student);
    }
}
