package com.sugang.toys.command.course.domain;

import com.sugang.toys.command.common.exception.ErrorCode;
import com.sugang.toys.command.course.domain.exception.CourseException;
import com.sugang.toys.command.department.domain.Department;
import com.sugang.toys.command.professor.domain.Professor;
import com.sugang.toys.command.subject.domain.Subject;
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

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "subject_id")
    private Subject subject;

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

    private String bookName;

    @Embedded
    private CourseSummaries courseSummaries;

    @Embedded
    private Prerequisite prerequisite;

    @Column(name = "professor_id")
    private Long professorId;

    @Column(name = "department_id")
    private Long departmentId;

    protected Course(
            Long id
            , Set<CourseSchedule> courseScheduleList
            , Set<CourseSummary> courseSummaries
            , Set<Long> preCourseIdSet
            , String bookName
            , Professor professor
            , String name
            , Long departmentId
            , CourseStatus courseStatus)
    {
        this.id = id;
        this.professorId = professor.getId();
        this.courseStatus = courseStatus;
        this.bookName = bookName;
        this.courseSummaries = new CourseSummaries(courseSummaries);
        this.courseSchedules = new CourseSchedules(courseScheduleList);
        this.prerequisite = new Prerequisite(preCourseIdSet);
        this.name = new CourseName(name);
        this.departmentId = departmentId;
    }

    public static Course createCourse(
            Set<CourseSchedule> openCourseScheduleSet
            , Set<CourseSummary> courseSummaries
            , Professor professor
            , String courseName
            , Department department
            , String bookName
            , CreateCourseValidator createCourseValidator)
    {
        createCourseValidator.validate(professor, department, courseName, openCourseScheduleSet);

        return new Course(
                null
                , openCourseScheduleSet
                , courseSummaries
                , null
                , bookName
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

    public void assign(Set<CourseSchedule> professorCourseSchedule, Professor professor)
    {
        if (this.professorId != null)
        {
            throw new CourseException(ErrorCode.INTERNAL_LOGIC_ERROR);
        }


    }
}
