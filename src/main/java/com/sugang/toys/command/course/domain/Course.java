package com.sugang.toys.command.course.domain;

import com.sugang.toys.command.department.domain.Department;
import com.sugang.toys.command.professor.domain.Professor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.util.Set;

@Getter
@Table(name = "course")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@ToString
public class Course {

    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "course_status")
    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

    @Embedded
    private CourseName name;

    public String getName()
    {
        return name.getValue();
    }

    @Embedded
    private CourseSchedules courseSchedules;

    @Embedded
    private Prerequisite prerequisite;

    @Column(name = "professor_id")
    private Long professorId;

    @Column(name = "department_id")
    private Long departmentId;

    protected Course(
            Long id
            , Set<CourseSchedule> courseScheduleList
            , Set<Long> preCourseIdSet
            , Professor professor
            , String name
            , Long departmentId
            , CourseStatus courseStatus)
    {
        this.id = id;
        this.professorId = professor.getId();
        this.courseStatus = courseStatus;
        this.courseSchedules = new CourseSchedules(courseScheduleList);
        this.prerequisite = new Prerequisite(preCourseIdSet);
        this.name = new CourseName(name);
        this.departmentId = departmentId;
    }

    public static Course newCreate(
            Long id
            , Set<CourseSchedule> courseScheduleList
            , Set<Long> preCourseIdSet
            , Professor professor
            , String name
            , Long departmentId
         )
    {
        return new Course(
                id
                , courseScheduleList
                , preCourseIdSet
                , professor
                , name
                , departmentId
                , CourseStatus.OPEN);
    }

    public static Course createCourse(
            Set<CourseSchedule> openCourseScheduleSet
            , Professor professor
            , String courseName
            , Department department
            , CreateCourseValidator createCourseValidator)
    {
        createCourseValidator.validate(professor, department, courseName, openCourseScheduleSet);

        return new Course(
                null
                , openCourseScheduleSet
                , null
                , professor
                , courseName
                , department.getId()
                , CourseStatus.HOLD);
    }

    public boolean isClosed()
    {
        return CourseStatus.CLOSE.equals(this.courseStatus);
    }

    public void close()
    {
        if (isClosed())
        {
            throw new RuntimeException("잘못된 접근입니다.");
        }
        this.courseStatus = CourseStatus.CLOSE;
    }

    public void open()
    {
        if (!isClosed() || CourseStatus.OPEN.equals(this.courseStatus))
        {
            throw new RuntimeException("잘못된 접근입니다.");
        }

        this.courseStatus = CourseStatus.OPEN;
    }
}
