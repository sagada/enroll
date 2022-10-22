package com.sugang.toys.command.subject.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubjectInfo {

    @Column(name = "introduction")
    private String introduction;

    private String bookName;
}
