package com.sugang.app.api.service.course.request;

import com.sugang.app.api.controller.course.dto.request.CourseScheduleRequest;
import com.sugang.app.api.controller.course.dto.request.CourseSummaryRequest;
import com.sugang.app.domain.course.CourseExamination;
import com.sugang.app.domain.course.CourseSchedule;
import com.sugang.app.domain.course.CourseSummary;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Builder
public record CourseCreateServiceRequest(
        String courseName,
        Long professorId,
        String bookName,
        Long subjectId,
        Set<CourseScheduleRequest> courseScheduleRequestSet,
        Set<CourseSummaryRequest> courseSummaryRequestSet,
        LocalDateTime midTermExamDate, 
        LocalDateTime finalExamDate,
        Set<Long> preCourseIdSet, 
        int score, 
        int availableStudentCount
) {
    public CourseExamination convertExamination()
    {
        if (midTermExamDate == null || finalExamDate == null)
        {
            return null;
        }

        return new CourseExamination(midTermExamDate(), finalExamDate());
    }

    public Set<CourseSummary> convertCourseSummary()
    {
        return courseSummaryRequestSet.stream()
                .map(CourseSummaryRequest::from)
                .collect(Collectors.toSet());
    }

    public Set<CourseSchedule> convertCourseSchedules()
    {
        return this.courseScheduleRequestSet.stream()
                .map(dto-> new CourseSchedule(dto.from(), dto.to(), dto.roomNumber()))
                .collect(Collectors.toSet());
    }
}
