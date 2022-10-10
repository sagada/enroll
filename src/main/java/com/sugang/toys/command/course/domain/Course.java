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
    @JoinColumn(name = "professor_id")
    private Professor professor;

    @Column(name = "department_id")
    private Long departmentId;

    @Column(name = "max_stu_cnt")
    private Integer maxStudentCount;

    protected Course(
            Long id
            , Set<CourseSchedule> courseScheduleList
            , Set<Long> preCourseIdSet
            , Professor professor
            , String name
            , Long departmentId
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
        this.departmentId = departmentId;
    }

    public static Course newCreate(
            Long id
            , Set<CourseSchedule> courseScheduleList
            , Set<Long> preCourseIdSet
            , Professor professor
            , String name
            , Long departmentId
            , Integer maxStudentCount)
    {
        return new Course(
                id
                , courseScheduleList
                , preCourseIdSet
                , professor
                , name
                , departmentId
                , CourseStatus.OPEN
                , maxStudentCount);
    }

    public static Course createCourse(
            Set<CourseSchedule> courseScheduleList
            , Professor professor
            , String courseName
            , Department department
            , Integer maxStudentCount
            , CreateCourseValidator createCourseValidator)
    {
        if (professor == null)
        {
            throw new RuntimeException("professor is null");
        }

        if (department == null)
        {
            throw new RuntimeException("department is null");
        }

        createCourseValidator.validate(professor, courseName, courseScheduleList);

        return new Course(
                null
                , courseScheduleList
                , null
                , professor
                , courseName
                , department.getId()
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

    public void open()
    {
        if (!isClosed())
        {
            throw new RuntimeException("열린 수강!..");
        }

        this.courseStatus = CourseStatus.OPEN;
    }
}
