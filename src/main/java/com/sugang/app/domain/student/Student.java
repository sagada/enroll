package com.sugang.app.domain.student;

import com.sugang.app.domain.BaseEntity;
import com.sugang.app.domain.student.exception.StudentException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Comment;

import javax.persistence.*;

@Entity
@Getter
@Table(name = "student")
@NoArgsConstructor
public class Student extends BaseEntity {

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
            int score,
            Long advisorProfessorId,
            Long departmentId
    )
    {
        return new Student(
                null, name, email, departmentId, academicYear, advisorProfessorId, score
        );
    }

    private Student(Long id, String name, String email, Long departmentId, Integer academicYear, Long advisorProfessorId, Integer availableScore) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.departmentId = departmentId;
        this.academicYear = academicYear;
        this.advisorProfessorId = advisorProfessorId;
        this.availableScore = availableScore;
    }

    private void setAcademicYear(int academicYear)
    {
        if (academicYear < 1 || academicYear > 5)
        {
            throw new StudentException("academicYear error");
        }

        this.academicYear = academicYear;
    }
    
    public void decreaseScore(int score)
    {
        if (this.availableScore - score < 0)
        {
            throw new IllegalStateException("Insufficient course credit");
        }

        this.availableScore -= score;
    }
}
