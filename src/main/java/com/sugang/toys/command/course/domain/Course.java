package com.sugang.toys.command.course.domain;

import com.sugang.toys.command.common.exception.ErrorCode;
import com.sugang.toys.command.course.domain.exception.CourseException;
import com.sugang.toys.command.course.domain.validator.CreateCourseValidator;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Getter
@Table(name = "course")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Course {

    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject_id")
    private Long subjectId;

    @Column(name = "course_status")
    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

    @Embedded
    private CourseName courseName;

    @Embedded
    private CourseSchedules courseSchedules;

    @Column(name = "course_score")
    private int score;

    @Embedded
    private CourseSummaries courseSummaries;

    @Embedded
    private CourseExamination courseExamination;

    @Embedded
    private PrerequisiteCourse prerequisiteCourse;

    @Column(name = "professor_id")
    private Long professorId;

    protected Course(
            Set<CourseSchedule> courseScheduleList
            , CourseExamination courseExamination
            , Set<CourseSummary> courseSummaries
            , Set<Long> preCourseIdSet
            , Long professorId
            , Long subjectId
            , CourseName courseName
            , CourseStatus courseStatus
            , int score)
    {
        setProfessor(professorId);
        setSubjectId(subjectId);
        setCourseName(courseName);
        setScore(score);
        setCourseExamination(courseExamination);
        this.courseStatus = courseStatus;
        this.courseSummaries = new CourseSummaries(courseSummaries);
        this.courseSchedules = new CourseSchedules(courseScheduleList);
        this.prerequisiteCourse = new PrerequisiteCourse(preCourseIdSet);
    }

    private void setProfessor(Long professorId)
    {
        if (professorId == null)
        {
            throw new CourseException("professorId is null");
        }

        this.professorId = professorId;
    }

    private void setSubjectId(Long subjectId)
    {
        if (subjectId == null)
        {
            throw new CourseException("subjectId is null");
        }

        this.subjectId = subjectId;
    }

    private void setCourseExamination(CourseExamination courseExamination)
    {
        if (courseExamination == null)
        {
            throw new CourseException("courseExamination is null");
        }

        this.courseExamination = courseExamination;
    }

    private void setScore(int score)
    {
        if (score <= 0 || score > 4)
        {
            throw new CourseException(ErrorCode.INVALID_COURSE_SCORE);
        }

        this.score = score;
    }

    private void setCourseName(CourseName courseName)
    {
        if (courseName == null)
        {
            throw new CourseException("courseName is null");
        }

        this.courseName = courseName;
    }

    public static Course createCourse(
            Set<CourseSchedule> openCourseScheduleSet
            , Set<CourseSummary> courseSummaries
            , CourseExamination courseExamination
            , Set<Long> preCourseIdSet
            , Long professorId
            , Long subjectId
            , CourseName courseName
            , int score
            , CreateCourseValidator createCourseValidator)
    {
        Course course = new Course(
                openCourseScheduleSet
                , courseExamination
                , courseSummaries
                , preCourseIdSet
                , professorId
                , subjectId
                , courseName
                , CourseStatus.HOLD
                , score
        );

        createCourseValidator.validate(course);
        return course;
    }

    public boolean isClosed()
    {
        return CourseStatus.CLOSE.equals(this.courseStatus);
    }

    public void close()
    {
        if (isClosed())
        {
            throw new CourseException("already closed");
        }

        this.courseStatus = CourseStatus.CLOSE;
    }

    public void open()
    {
        if (CourseStatus.OPEN.equals(this.courseStatus))
        {
            throw new CourseException("already open");
        }

        this.courseStatus = CourseStatus.OPEN;
    }

}
