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
    @Column(unique = true)
    private CourseName name;

    public String getName()
    {
        return name.getValue();
    }

    @Embedded
    private CourseSchedules courseSchedules;

    @Embedded
    private PreCourses preCourses;

    @Column(name = "professor_id")
    private Long professorId;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "max_stu_cnt")
    private Integer maxStudentCount;

    protected Course(
            Long id
            , Set<CourseSchedule> courseScheduleList
            , Set<Long> preCourseIdSet
            , Long professorId
            , String name
            , Long departmentId
            , CourseStatus courseStatus
            , Integer maxStudentCount)
    {
        this.id = id;
        this.professorId = professorId;
        this.maxStudentCount = maxStudentCount;
        this.courseStatus = courseStatus;
        this.courseSchedules = new CourseSchedules(courseScheduleList);
        this.preCourses = new PreCourses(preCourseIdSet);
        this.name = new CourseName(name);
        this.departmentId = departmentId;
    }

    public static Course newCreate(
            Long id
            , Set<CourseSchedule> courseScheduleList
            , Set<Long> preCourseIdSet
            , Long professorId
            , String name
            , Long departmentId
            , Integer maxStudentCount)
    {
        return new Course(
                id
                , courseScheduleList
                , preCourseIdSet
                , professorId
                , name
                , departmentId
                , CourseStatus.OPEN
                , maxStudentCount);
    }

    public static Course createCourse(
            Set<CourseSchedule> courseScheduleList
            , Professor professor
            , String name
            , Department department
            , Integer maxStudentCount
            , CreateCourseValidator createCourseValidator)
    {
        if (courseScheduleList.isEmpty())
        {
            throw new IllegalStateException("schedule is empty!");
        }

        if (!professor.isWorking())
        {
            throw new IllegalStateException("professor is not working!");
        }

        createCourseValidator.duplicateCourseName(name);
        createCourseValidator.professorScheduleCheck(professor, courseScheduleList);

        return new Course(
                null
                , courseScheduleList
                , null
                , professor
                , name
                , department
                 , CourseStatus.HOLD
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

    public void createCourse()
    {
        if (isClosed())
        {
            this.courseStatus = CourseStatus.OPEN;
        }
    }
}
