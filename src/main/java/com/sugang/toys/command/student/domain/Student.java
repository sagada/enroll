package com.sugang.toys.command.student.domain;

import com.sugang.toys.command.student.domain.exception.StudentException;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Table(name = "student")
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "student_id")
    private Long id;

    private String name;

    @Column(name = "email", unique = true)
    private String email;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "academic_year")
    private Integer academicYear;

    @Column(name = "advisorProfessor_id")
    private Long advisorProfessorId;

    @OneToMany(fetch = FetchType.LAZY)
    private List<StudentRegistrationInfo> studentRegistrationInfos;

    public Student(String name, int academicYear, Long departmentId)
    {
        if (name.isBlank())
        {
            throw new StudentException("학생 이름 누락");
        }
        this.name = name;
        this.departmentId = departmentId;
        setAcademicYear(academicYear);
    }

    private void setAcademicYear(int academicYear)
    {
        if (academicYear < 1 || academicYear > 4)
        {
            throw new StudentException("academicYear error");
        }

        this.academicYear = academicYear;
    }

    public void registerSemester(StudentRegistrationInfo studentRegistrationInfo) {

        if (studentRegistrationInfos == null)
        {
            studentRegistrationInfos = new ArrayList<>();
        }

        studentRegistrationInfos.add(studentRegistrationInfo);
    }
}
