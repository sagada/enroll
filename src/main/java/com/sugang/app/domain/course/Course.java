package com.sugang.app.domain.course;

import com.sugang.app.domain.BaseEntity;
import com.sugang.app.domain.course.exception.CourseException;
import com.sugang.app.domain.course.validator.CreateCourseValidator;
import com.sugang.app.global.exception.ErrorCode;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Set;

@Getter
@Table(name = "course")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Course extends BaseEntity {

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

    @ElementCollection
    @CollectionTable(name = "course_scheduler", joinColumns = @JoinColumn(name = "course_id"))
    private Set<CourseSchedule> courseScheduleSet;

    @Column(name = "course_score")
    private int score;

    @Embedded
    private Syllabus syllabus;

    @Embedded
    private CourseExamination courseExamination;

    @Embedded
    private PrerequisiteCourse prerequisiteCourse;

    @Column(name = "professor_id")
    private Long professorId;

    private int availStudentCount;

    protected Course(
            Set<CourseSchedule> courseScheduleList
            , CourseExamination courseExamination
            , Set<CourseSummary> courseSummaries
            , Set<Long> preCourseIdSet
            , CourseName courseName
            , CourseStatus courseStatus
            , int score
            , Long subjectId
            , Long professorId
            , int availStudentCount)
    {
        setProfessor(professorId);
        setSubjectId(subjectId);
        setCourseName(courseName);
        setScore(score);
        setCourseExamination(courseExamination);
        this.courseStatus = courseStatus;
        this.syllabus = new Syllabus(courseSummaries);
        this.courseScheduleSet = courseScheduleList;
        this.prerequisiteCourse = new PrerequisiteCourse(preCourseIdSet);
        this.availStudentCount = availStudentCount;
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
            return ;
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
            , CreateCourseValidator createCourseValidator
            , int availStudentCount)
    {
        Course course = new Course(
                openCourseScheduleSet
                , courseExamination
                , courseSummaries
                , preCourseIdSet
                , courseName
                , CourseStatus.HOLD
                , score
                , subjectId
                , professorId
                , availStudentCount
        );

        createCourseValidator.validate(course);
        return course;
    }

    public void setAvailStudentCount(int availStudentCount)
    {
        this.availStudentCount = availStudentCount;
    }

    public void update(Course updateCourse)
    {
        setCourseExamination(updateCourse.getCourseExamination());
        this.setProfessor(updateCourse.getProfessorId());
        this.setSubjectId(updateCourse.getSubjectId());
        this.setCourseName(updateCourse.getCourseName());
        this.syllabus = updateCourse.getSyllabus();
        this.courseScheduleSet = updateCourse.getCourseScheduleSet();
        this.courseStatus = updateCourse.getCourseStatus();
        this.professorId = updateCourse.getProfessorId();
        this.score = updateCourse.getScore();
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

    public void addStudent()
    {
        if (isClosed())
        {
            throw new CourseException("already closed");
        }

        if (availStudentCount - 1 < 0)
        {
            throw new IllegalStateException("Exceeded number of students");
        }

        this.availStudentCount -= 1;
    }

    public boolean availableAddStudent()
    {
        return this.availStudentCount > 0 && !isClosed();
    }
}
