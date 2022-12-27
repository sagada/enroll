package com.sugang.toys.command.course.domain;

import com.sugang.toys.command.course.domain.exception.CourseException;
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
    private Semester semester;

    public String getSemester()
    {
        return semester.getSemeseter();
    }

    @Embedded
    private CourseName name;

    public String getName()
    {
        return name.getValue();
    }

    @Embedded
    private CourseSchedules courseSchedules;

    private String bookName;

    private int score;

    @Embedded
    private CourseSummaries courseSummaries;

    @Embedded
    private CourseExamination courseExamination;

    @Embedded
    private Prerequisite prerequisite;

    @Column(name = "professor_id")
    private Long professorId;

    @Embedded
    private StudentCount studentCount;

    protected Course(
            Long id
            , Set<CourseSchedule> courseScheduleList
            , Set<CourseSummary> courseSummaries
            , Set<Long> preCourseIdSet
            , String bookName
            , Long professorId
            , String name
            , CourseStatus courseStatus
            , int score)
    {
        this.id = id;
        this.professorId = professorId;
        this.courseStatus = courseStatus;
        this.bookName = bookName;
        this.courseSummaries = new CourseSummaries(courseSummaries);
        this.courseSchedules = new CourseSchedules(courseScheduleList);
        this.prerequisite = new Prerequisite(preCourseIdSet);
        this.name = new CourseName(name);
        this.score = score;
    }

    public static Course Create(
            Set<CourseSchedule> courseScheduleSet
            , Set<CourseSummary> courseSummaries
            , Set<Long> preCourseIdSet
            , String bookName
            , Long professorId
            , String name
            , CourseStatus courseStatus
            , int score)
    {
        return new Course(
                null,
                courseScheduleSet
                , courseSummaries
                ,preCourseIdSet
                , bookName
                , professorId
                , name
                , courseStatus
                , score
        );
    }

    public static Course createCourse(
            Set<CourseSchedule> openCourseScheduleSet
            , Set<CourseSummary> courseSummaries
            , Long professorId
            , Long subjectId
            , String courseName
            , String bookName
            , int score
            , CreateCourseValidator createCourseValidator)
    {
        createCourseValidator.validate(subjectId, professorId, courseName, openCourseScheduleSet);

        return new Course(
                null
                , openCourseScheduleSet
                , courseSummaries
                , null
                , bookName
                , professorId
                , courseName
                , CourseStatus.HOLD
                , score);
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
            throw new CourseException("이미 할당 된 교수가 있습니다.");
        }

        CourseSchedules courseSchedules = this.getCourseSchedules();
        Set<CourseSchedule> courseScheduleSet = courseSchedules.getCourseScheduleSet();
    }

    public void addStudent()
    {
        int enrollStudentCount = this.studentCount.getEnrollStudentCount();
        int studentMaxCount = this.studentCount.getStudentMaxCount();
        this.studentCount = new StudentCount(studentMaxCount, enrollStudentCount + 1);
    }

    public boolean enrollFinished()
    {
        return this.studentCount.overStudentCount();
    }
}
