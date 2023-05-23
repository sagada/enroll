package com.sugang.app.api.service.course.request;

import com.sugang.app.api.controller.course.dto.request.CourseScheduleRequest;
import com.sugang.app.api.controller.course.dto.request.CourseSummaryRequest;
import com.sugang.app.domain.course.domain.CourseExamination;
import com.sugang.app.domain.course.domain.CourseSchedule;
import com.sugang.app.domain.course.domain.CourseSummary;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@NoArgsConstructor
public class CourseCreateServiceRequest {

    private String courseName;
    private Long professorId;
    private String bookName;
    private Long subjectId;
    private Set<CourseScheduleRequest> courseScheduleRequestSet;
    private Set<CourseSummaryRequest> courseSummaryRequestSet;
    private LocalDateTime midTermExamDate;
    private LocalDateTime finalExamDate;
    private Set<Long> preCourseIdSet;
    private Integer score;

    @Builder
    private CourseCreateServiceRequest(
            String courseName,
            Long professorId,
            String bookName,
            Long subjectId,
            Set<CourseScheduleRequest> courseScheduleRequestSet,
            Set<CourseSummaryRequest> courseSummaryRequestSet,
            LocalDateTime midTermExamDate,
            LocalDateTime finalExamDate,
            Set<Long> preCourseIdSet,
            Integer score)
    {
        this.courseName = courseName;
        this.professorId = professorId;
        this.bookName = bookName;
        this.subjectId = subjectId;
        this.courseScheduleRequestSet = courseScheduleRequestSet;
        this.courseSummaryRequestSet = courseSummaryRequestSet;
        this.midTermExamDate = midTermExamDate;
        this.finalExamDate = finalExamDate;
        this.preCourseIdSet = preCourseIdSet;
        this.score = score;
    }

    public Set<CourseSchedule> convertCourseSchedules()
    {
        return courseScheduleRequestSet.stream()
                .map(CourseScheduleRequest::from)
                .collect(Collectors.toSet());
    }

    public CourseExamination convertExamination()
    {
        return new CourseExamination(getMidTermExamDate(), getFinalExamDate());
    }
    public Set<CourseSummary> convertCourseSummary()
    {
        return courseSummaryRequestSet.stream()
                .map(CourseSummaryRequest::from)
                .collect(Collectors.toSet());
    }

}
