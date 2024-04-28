package com.sugang.app.api.controller.course.dto.request;

import com.sugang.app.api.service.course.request.CourseCreateServiceRequest;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDateTime;
import java.util.Set;

public record CourseCreateRequest(
        @NotBlank(message = "수업 이름은 필수 값입니다.")
        String courseName,

        Long professorId,

        String bookName,

        @NotNull(message = "과목 ID는 필수 값입니다.")
        Long subjectId,

        Set<CourseScheduleRequest> courseScheduleRequestSet,

        Set<CourseSummaryRequest> courseSummaryRequestSet,

        LocalDateTime midTermExamDate,

        LocalDateTime finalExamDate,

        Set<Long> preCourseIdSet,

        @NotNull(message = "학점은 필수 값입니다.")
        @Max(value = 5, message = "학점은 5점 이하입니다.")
        @Min(value = 1, message = "학점은 1점 이상입니다.")
        Integer score
) {
    public CourseCreateServiceRequest toServiceDto() {
        return CourseCreateServiceRequest.builder()
                .bookName(bookName)
                .courseName(courseName)
                .courseScheduleRequestSet(courseScheduleRequestSet)
                .courseSummaryRequestSet(courseSummaryRequestSet)
                .finalExamDate(finalExamDate)
                .midTermExamDate(midTermExamDate)
                .preCourseIdSet(preCourseIdSet)
                .professorId(professorId)
                .score(score)
                .subjectId(subjectId)
                .build();
    }
}
