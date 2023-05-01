package com.sugang.app.domain.subject.domain;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Table(name = "subject")
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
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
