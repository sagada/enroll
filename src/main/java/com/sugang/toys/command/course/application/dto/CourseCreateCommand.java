package com.sugang.toys.command.course.application.dto;

import com.sugang.toys.command.course.domain.CourseSchedule;
import com.sugang.toys.command.course.domain.CourseSummary;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class CourseCreateCommand {

    private String courseName;
    private Long professorId;
    private String bookName;
    private Long subjectId;
    private Set<CourseScheduleRequest> courseScheduleRequestSet;
    private Set<CourseSummaryRequest> courseSummaryRequestSet;
    private int score;

    public Set<CourseSchedule> convertCourseSchedules()
    {
        return courseScheduleRequestSet.stream()
                .map(CourseScheduleRequest::from)
                .collect(Collectors.toSet());
    }

    public Set<CourseSummary> convertCourseSummary()
    {
        return courseSummaryRequestSet.stream()
                .map(CourseSummaryRequest::from)
                .collect(Collectors.toSet());
    }
}
