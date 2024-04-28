package com.sugang.app.domain.subject;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class SubjectInfo {

    @Column(name = "introduction")
    private String introduction;

    @Column(name = "book_name")
    private String bookName;
}
