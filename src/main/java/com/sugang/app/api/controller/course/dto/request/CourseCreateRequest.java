package com.sugang.app.api.controller.course.dto.request;

import com.sugang.app.api.service.course.request.CourseCreateServiceRequest;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import javax.validation.constraints.*;
import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
@Accessors(chain = true)
@NoArgsConstructor
public class CourseCreateRequest {

    @NotBlank(message = "수업 이름은 필수 값입니다.")
    private String courseName;

    private Long professorId;

    private String bookName;

    @NotNull(message = "과목 ID는 필수 값입니다.")
    private Long subjectId;

    private Set<CourseScheduleRequest> courseScheduleRequestSet;
    private Set<CourseSummaryRequest> courseSummaryRequestSet;

    private LocalDateTime midTermExamDate;
    private LocalDateTime finalExamDate;

    private Set<Long> preCourseIdSet;

    @NotNull(message = "학점은 필수 값입니다.")
    @Max(value = 5, message = "학점은 5점 이하입니다.")
    @Min(value = 1, message = "학점은 1점 이상입니다.")
    private Integer score;

    @Builder
    private CourseCreateRequest(
            String courseName,
            Long professorId,
            String bookName,
            Long subjectId,
            Set<CourseScheduleRequest> courseScheduleRequestSet,
            Set<CourseSummaryRequest> courseSummaryRequestSet,
            LocalDateTime midTermExamDate,
            LocalDateTime finalExamDate,
            Set<Long> preCourseIdSet,
            int score)
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

    public CourseCreateServiceRequest toServiceDto()
    {
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
