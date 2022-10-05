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
    private PreCourses preCourses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prof_id")
    @ToString.Exclude
    private Professor professor;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "depart_id")
    @ToString.Exclude
    private Department department;

    @Column(name = "max_stu_cnt")
    private Integer maxStudentCount;

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void setProfessor(Professor professor) {
        this.professor = professor;
    }

    protected Course(
            Long id
            , Set<CourseSchedule> courseScheduleList
            , Set<Long> preCourseIdSet
            , Professor professor
            , String name
            , Department department
            , CourseStatus courseStatus
            , Integer maxStudentCount)
    {
        this.id = id;
        this.professor = professor;
        this.maxStudentCount = maxStudentCount;
        this.courseStatus = courseStatus;
        this.courseSchedules = new CourseSchedules(courseScheduleList);
        this.preCourses = new PreCourses(preCourseIdSet);
        this.name = new CourseName(name);
        this.department = department;
    }

    public static Course create(
            Long id
            , Set<CourseSchedule> courseScheduleList
            , Set<Long> preCourseIdSet
            , Professor professor
            , String name
            , Department department
            , Integer maxStudentCount)
    {
        return new Course(
                id
                , courseScheduleList
                , preCourseIdSet
                , professor
                , name
                , department
                , CourseStatus.OPEN
                , maxStudentCount);
    }

    public static Course open(
            Set<CourseSchedule> courseScheduleList
            , Professor professor
            , String name
            , Department department
            , Integer maxStudentCount)
    {
        return new Course(
                null
                , courseScheduleList
                , null
                , professor
                , name
                , department
                , CourseStatus.OPEN
                , maxStudentCount);
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
}
