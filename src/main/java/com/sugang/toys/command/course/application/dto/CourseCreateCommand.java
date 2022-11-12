package com.sugang.toys.command.course.application.dto;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.Set;

@Data
@NoArgsConstructor
@Accessors(chain = true)
public class CourseCreateCommand {

    private String courseName;
    private Long departmentId;
    private Long professorId;
    private String bookName;
    private Set<CourseScheduleRequest> courseScheduleSet;
    private Set<CourseSummaryRequest> courseSummaryRequestSet;
    private int score;
    public CourseCreateCommand(
            String courseName
            , Long departmentId
            , Long professorId
            , String bookName
            , Set<CourseScheduleRequest> courseScheduleSet
            , Set<CourseSummaryRequest> courseSummaryRequestSet
    )
    {
        this.courseName = courseName;
        this.departmentId = departmentId;
        this.bookName = bookName;
        this.professorId = professorId;
        this.courseScheduleSet = courseScheduleSet;
        this.courseSummaryRequestSet = courseSummaryRequestSet;
    }
}
