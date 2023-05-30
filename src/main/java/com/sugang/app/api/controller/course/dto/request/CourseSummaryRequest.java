package com.sugang.app.api.controller.course.dto.request;

import com.sugang.app.domain.course.domain.CourseSummary;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(of = "week")
@Data
public class CourseSummaryRequest {
    private final int week;
    private final String content;
    private final String title;

    @Builder
    private CourseSummaryRequest(int week, String content, String title)
    {
        this.week = week;
        this.content = content;
        this.title = title;
    }

    public static CourseSummary from(CourseSummaryRequest request)
    {
        return new CourseSummary(request.getWeek(), request.getContent(), request.getTitle());
    }
}
