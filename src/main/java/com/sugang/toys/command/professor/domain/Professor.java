package com.sugang.toys.command.professor.domain;

import com.sugang.toys.command.common.domain.PhoneNumber;
import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.course.domain.CourseSchedule;
import com.sugang.toys.command.department.domain.Department;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Table(name = "professor")
@Getter
@Entity
@ToString
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private PhoneNumber phoneNumber;

    private String name;

    @Enumerated(EnumType.STRING)
    private ProfessorStatus professorStatus;

    public Professor(Long id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public Course openCourse(
            Set<CourseSchedule> openCourseScheduleList
            , String name
            , Department department
            , Integer maxStudentCount
            , OpenCourseScheduleValidator openCourseScheduleValidateService)
    {
        validateOpenCourse(openCourseScheduleValidateService, openCourseScheduleList);
        return Course.open(openCourseScheduleList, this, name, department, maxStudentCount);
    }

    private void validateOpenCourse(
            OpenCourseScheduleValidator openCourseScheduleValidateService
            , Set<CourseSchedule> courseScheduleList)
    {
        if (!professorStatus.equals(ProfessorStatus.WORK))
        {
            throw new IllegalStateException("NOT WORKING PROFESSOR!");
        }

        openCourseScheduleValidateService.openCourseScheduleCheck(this, courseScheduleList);
    }

    public Professor(Long id, PhoneNumber phoneNumber, String name, ProfessorStatus professorStatus)
    {
        this.id = id;
        this.phoneNumber = phoneNumber;
        this.name = name;
        this.professorStatus = professorStatus;
    }

    public static Professor create(
            Long id
            , PhoneNumber phoneNumber
            , String name
            , ProfessorStatus professorStatus
    )
    {
        return new Professor(id, phoneNumber, name, professorStatus);
    }
}
