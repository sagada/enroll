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
            Set<CourseSchedule> courseScheduleList
            , String name
            , Department department
            , Integer maxStudentCount
            , ProfessorOpenCourseValidateService professorOpenCourseValidateService)
    {
        invalidOpenCourseCheck(professorOpenCourseValidateService, courseScheduleList);
        return Course.open(courseScheduleList, this, name, department, maxStudentCount);
    }

    private void invalidOpenCourseCheck(
            ProfessorOpenCourseValidateService professorOpenCourseValidateService
            , Set<CourseSchedule> courseScheduleList)
    {
        professorOpenCourseValidateService.validate(this, courseScheduleList);

        if (!professorStatus.equals(ProfessorStatus.WORK))
        {
            throw new IllegalStateException("WORK 아닌 교수는 개설을 할 수 없습니다.");
        }
    }
}
