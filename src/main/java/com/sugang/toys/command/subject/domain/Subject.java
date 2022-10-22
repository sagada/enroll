package com.sugang.toys.command.subject.domain;

import javax.persistence.*;

@Table(name = "subject")
@Entity
public class Subject {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "subject_name")
    private String subjectName;

    @Column(name = "is_pre_requisite")
    private boolean isPrerequisite;

    @Embedded
    private SubjectInfo subjectInfo;
}
