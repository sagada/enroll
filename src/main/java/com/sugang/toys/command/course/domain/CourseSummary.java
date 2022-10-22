package com.sugang.toys.command.course.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class CourseSummary {

    @Column(name = "week")
    private int week;

    @Column(name = "content")
    private String content;

    @Column(name = "title")
    private String title;

    public CourseSummary(int week, String content, String title) {
        this.week = week;
        this.content = content;
        this.title = title;
    }
}
