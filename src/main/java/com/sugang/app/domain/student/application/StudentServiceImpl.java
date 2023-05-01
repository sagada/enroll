package com.sugang.app.domain.student.application;

import com.sugang.app.domain.student.domain.Student;
import com.sugang.app.domain.student.domain.StudentRepository;
import com.sugang.app.domain.student.domain.CreateStudentValidate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService{

    private final StudentRepository studentRepository;
    private final CreateStudentValidate createStudentValidate;
    @Autowired
    public StudentServiceImpl(
            StudentRepository studentRepository
            , CreateStudentValidate createStudentValidate)
    {
        this.studentRepository = studentRepository;
        this.createStudentValidate = createStudentValidate;
    }

    @Override
    public Student save(CreateStudentDto createStudentDto) {

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

    @Override
    public boolean registerSemester(Student student) {
        return false;
    }
}
