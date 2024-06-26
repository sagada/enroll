package com.sugang.app.domain.student.infra;

import com.sugang.app.domain.department.Department;
import com.sugang.app.domain.department.DepartmentRepository;
import com.sugang.app.domain.professor.Professor;
import com.sugang.app.domain.professor.ProfessorRepository;
import com.sugang.app.domain.student.CreateStudentValidate;
import com.sugang.app.domain.student.Student;
import com.sugang.app.domain.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateStudentValidateImpl implements CreateStudentValidate {

    private final StudentRepository studentRepository;
    private final DepartmentRepository departmentRepository;
    private final ProfessorRepository professorRepository;

    @Override
    public void validate(Student student)
    {
        validate(student, student.getAdvisorProfessorId(), student.getDepartmentId());
    }

    private void validate(Student student, Long advisorProfessorId, Long departmentId)
    {
        // email 중복 체크
        if (studentRepository.existsByEmail(student.getEmail()))
        {
            throw new IllegalArgumentException("이메일 중복");
        }

        if (student.getAcademicYear() == null)
        {
            throw new IllegalArgumentException("학년 미입력");
        }

        // department 조회
        Department department = departmentRepository.findById(departmentId).orElseThrow(() -> new IllegalStateException("없는 학부"));

        // professor 조회
        Professor professor = professorRepository.findById(advisorProfessorId).orElseThrow(() -> new IllegalStateException("없는 교수"));

        if (!professor.working())
        {
            throw new IllegalArgumentException("교수 휴직 상태");
        }
    }
}
