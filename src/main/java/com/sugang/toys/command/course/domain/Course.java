package com.sugang.toys.command.course.domain;

import com.sugang.toys.command.professor.domain.Professor;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
public class Course {

    @Id
    @Column(name = "course_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    private CourseStatus courseStatus;

    @Embedded
    private CourseName name;

    @Embedded
    private CourseSchedules courseSchedules;

    @Embedded
    private PreCourses preCourses;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "prof_id")
    private Professor professor;

    @Column(name = "max_stu_cnt")
    private Integer maxStudentCount;

    protected Course(
            Long id
            , List<CourseSchedule> courseScheduleList
            , Set<Long> preCourseIdSet
            , Professor professor
            , String name
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
    }

    public static Course create(
            Long id
            , List<CourseSchedule> courseScheduleList
            , Set<Long> preCourseIdSet
            , Professor professor
            , String name
            , Integer maxStudentCount)
    {
        return new Course(
                id
                , courseScheduleList
                , preCourseIdSet
                , professor
                , name
                , CourseStatus.OPEN
                , maxStudentCount);
    }
}
