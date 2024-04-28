package com.sugang.app.domain.course;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Getter
@Embeddable
@EqualsAndHashCode(of = {"week", "content", "title"})
@NoArgsConstructor
public class CourseSummary {

    @Column(name = "course_week")
    private int week;

    @Column(name = "course_content")
    private String content;

    @Column(name = "course_title")
    private String title;

    public CourseSummary(int week, String content, String title)
    {
        this.week = week;
        this.content = content;
        this.title = title;
    }
}
