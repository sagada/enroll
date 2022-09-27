package com.sugang.toys.command.student.domain;

import com.sugang.toys.command.common.exception.ErrorCode;
import com.sugang.toys.command.department.domain.Departments;
import com.sugang.toys.command.student.domain.exception.StudentException;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@NoArgsConstructor
@Entity
@Getter
@ToString(exclude = "{departments}")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private StudentName name;

    @Embedded
    private StudentBirthDay birthDay;

    @Min(value = 1, message = "1학년 부터 ")
    @Max(value = 4, message = "4학년 초과에러")
    private Integer grade;

    @Enumerated(EnumType.STRING)
    private StudentStatus studentStatus;

    public String birthDay()
    {
        return birthDay.value();
    }

    public String studentName()
    {
        return name.value();
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "depart_id")
    private Departments departments;

    private Student(Long id, String name, Departments departments, String birthDay, StudentStatus studentStatus, int grade)
    {
        this.id = id;
        this.name = new StudentName(name);
        this.birthDay = new StudentBirthDay(birthDay);
        this.studentStatus = studentStatus;
        this.grade = grade;
        this.departments = departments;
    }

    public static Student enter(
            String name
            , Departments departments
            , String birthday
    )
    {
        return new Student(null, name, departments, birthday, StudentStatus.ATTENDING, 1);
    }

    public static Student create(
            Long id
            , String name
            , Departments departments
            , String birthday
            , StudentStatus studentStatus
            , int grade)
    {
       return new Student(id, name, departments, birthday, studentStatus, grade);
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
            throw new StudentException(ErrorCode.EXPEL_UPDATE_ERROR);
        }

        if (isGraduation())
        {
            throw new StudentException(ErrorCode.GRADUATION_UPDATE_ERROR);
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

    public void addOneGrade()
    {
        validateUpdateStudent();

        if (grade >= 4)
        {
            throw new StudentException(ErrorCode.EXCEED_GRADE_UPDATE_ERROR);
        }

        this.grade += 1;
    }

    public Long id()
    {
        return id;
    }

    public boolean isEnter()
    {
        return this.studentStatus.equals(StudentStatus.ENTER);
    }

    public void assignDepartment(Departments departments)
    {
        if (!this.studentStatus.equals(StudentStatus.ENTER))
        {
            throw new StudentException(ErrorCode.ASSIGN_DEPART_MENT_ERROR);
        }

        if (this.departments != null)
        {
            throw new StudentException(ErrorCode.ASSIGN_DEPART_MENT_ERROR);
        }

        this.departments = departments;
    }
}
