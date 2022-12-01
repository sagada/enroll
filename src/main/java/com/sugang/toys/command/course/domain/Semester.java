package com.sugang.toys.command.course.domain;

import javax.persistence.Embeddable;

@Embeddable
public class Semester {
    private String date;
    private String type;

    public Semester(String date, String type)
    {
        this.date = date;
        this.type = type;
    }

    public String getSemeseter()
    {
        return date + type;
    }
}
