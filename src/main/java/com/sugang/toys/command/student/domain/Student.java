package com.sugang.toys.command.student.domain;


import com.sugang.toys.command.student.domain.exception.StudentErrorCode;
import com.sugang.toys.command.student.domain.exception.StudentException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private StudentName name;

    @Embedded
    private StudentBirthDay birthDay;

    @Min(value = 1, message = "1학년부터...")
    @Max(value = 4, message = "4학년 초과에러")
    @Getter
    private Integer grade;

    @Enumerated(EnumType.STRING)
    @Getter
    private StudentStatus studentStatus;

    public String birthDay()
    {
        return birthDay.value();
    }

    public String studentName()
    {
        return name.value();
    }

    private Student(Long id, String name, Long departmentId, String birthDay, StudentStatus studentStatus, int grade)
    {
        this.id = id;
        this.name = new StudentName(name);
        this.birthDay = new StudentBirthDay(birthDay);
        this.studentStatus = studentStatus;
        this.grade = grade;
    }

    public static Student enter(
            String name
            , Long departmentId
            , String birthday
    )
    {
        return new Student(null, name, departmentId, birthday, StudentStatus.ATTENDING, 1);
    }

    public static Student create(
            Long id
            , String name
            , Long departmentId
            , String birthday
            , StudentStatus studentStatus
            , int grade)
    {
       return new Student(id, name, departmentId, birthday, studentStatus, grade);
    }

    public void update(Student student)
    {
        validateUpdateStudent();

        this.studentStatus = student.studentStatus;
        this.birthDay = new StudentBirthDay(student.birthDay());
        this.name = new StudentName(student.studentName());
    }

    private void validateUpdateStudent()
    {
        if (StudentStatus.EXPEL.equals(this.studentStatus))
        {
            throw new StudentException(StudentErrorCode.EXPEL_UPDATE_ERROR);
        }

        if (isGraduation())
        {
            throw new StudentException(StudentErrorCode.GRADUATION_UPDATE_ERROR);
        }
    }

    public void expel()
    {
        validateUpdateStudent();
        this.studentStatus = StudentStatus.EXPEL;
    }

    public boolean isGraduation()
    {
        return StudentStatus.GRADUATION.equals(this.studentStatus);
    }

    public void upgrade()
    {
        validateUpdateStudent();

        if (grade >= 4)
        {
            throw new StudentException(StudentErrorCode.EXCEED_GRADE_UPDATE_ERROR);
        }

        this.grade += 1;
    }
}
