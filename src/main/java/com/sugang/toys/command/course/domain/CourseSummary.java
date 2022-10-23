package com.sugang.toys.command.course.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
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
