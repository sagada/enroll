package com.sugang.app.domain.student;

import com.sugang.app.domain.student.exception.StudentException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "student")
@NoArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Column(unique = true)
    private String email;

    private Long departmentId;

    private Integer academicYear;

    private Long advisorProfessorId;

    @Comment("이수 가능 학점")
    private Integer availableScore;

    public Student(
            String name,
            int academicYear,
            Long departmentId,
            Long advisorProfessorId)
    {
        if (name.isBlank())
        {
            throw new StudentException("student name is blank");
        }

        this.name = name;
        this.departmentId = departmentId;
        setProfessorId(advisorProfessorId);
        setAcademicYear(academicYear);
    }

    private void setProfessorId(Long advisorProfessorId)
    {
        if (advisorProfessorId == null)
        {
            throw new StudentException("professor Id is null");
        }

        this.advisorProfessorId = advisorProfessorId;
    }

    public static Student createStudent(
            String name,
            String email,
            Integer academicYear,
            String score,
            Long advisorProfessorId,
            Long departmentId,
            CreateStudentValidate createStudentValidate)
    {
        Student student = new Student();
        createStudentValidate.validate(student);
        return student;
    }

    private void setAcademicYear(int academicYear)
    {
        if (academicYear < 1 || academicYear > 5)
        {
            throw new StudentException("academicYear error");
        }

        this.academicYear = academicYear;
    }
}
