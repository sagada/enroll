package com.sugang.app.domain.course.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

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
