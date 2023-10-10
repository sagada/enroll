package com.sugang.app.api.controller.course.dto.request;

import com.sugang.app.domain.course.CourseSummary;
import lombok.Builder;

@Builder
public record CourseSummaryRequest(
        int week,
        String content,
        String title
) {
    public static CourseSummary from(CourseSummaryRequest request)
    {
        return new CourseSummary(request.week(), request.content(), request.title());
    }
}
