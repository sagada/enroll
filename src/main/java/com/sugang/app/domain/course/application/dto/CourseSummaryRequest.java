package com.sugang.app.domain.course.application.dto;

import com.sugang.app.domain.course.domain.CourseSummary;
import lombok.Data;

@Data
public class CourseSummaryRequest {
    private final int week;
    private final String content;
    private final String title;

    public static CourseSummary from(CourseSummaryRequest request)
    {
        return new CourseSummary(request.getWeek(), request.getContent(), request.getTitle());
    }
}
