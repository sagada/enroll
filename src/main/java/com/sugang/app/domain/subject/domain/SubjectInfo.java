package com.sugang.app.domain.subject.domain;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class SubjectInfo {

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "book_name")
    private String bookName;
}
