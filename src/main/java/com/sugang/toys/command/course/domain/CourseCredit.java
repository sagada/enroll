package com.sugang.toys.command.course.domain;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@NoArgsConstructor
@Embeddable
public class CourseCredit {

    @Column(name = "mid_term_exam_score")
    private Integer midTermExamScore;

    @Column(name = "mid_term_exam_rank")
    private String midTermExamRank;

    @Column(name = "final_exam_score")
    private Integer finalExamScore;

    @Column(name = "final_exam_rank")
    private String finalTermExamRank;

    @Column(name = "course_grade")
    private String courseGrade;

}
