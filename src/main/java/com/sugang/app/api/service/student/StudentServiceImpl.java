package com.sugang.app.api.service.student;

import com.sugang.app.api.service.student.request.CreateStudentServiceDto;
import com.sugang.app.domain.student.CreateStudentValidate;
import com.sugang.app.domain.student.Student;
import com.sugang.app.domain.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final CreateStudentValidate createStudentValidate;

    @Override
    public Student save(CreateStudentServiceDto createStudentDto)
    {
        Student student = Student.createStudent(
                createStudentDto.getName(),
                createStudentDto.getEmail(),
                createStudentDto.getAcademicYear(),
                createStudentDto.getScore(),
                createStudentDto.getAdvisorProfessorId(),
                createStudentDto.getDepartmentId(),
                createStudentValidate
        );

        return studentRepository.save(student);
    }
}
