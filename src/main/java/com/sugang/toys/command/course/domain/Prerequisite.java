package com.sugang.toys.command.course.domain;

import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@NoArgsConstructor
@Embeddable
public class Prerequisite {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "pre_course", joinColumns = @JoinColumn(name = "course_id"))
    private Set<Long> preCourseSeqList;

    public Prerequisite(Set<Long> preCourseSeqList)
    {
        if (preCourseSeqList == null)
        {
            return ;
        }

        // TODO RuntimeException 제거
        preCourseSeqList
            .stream()
            .filter(preCourseSeq -> preCourseSeq <= 0L)
            .findAny()
            .orElseThrow(() -> new RuntimeException("선수과목 아이디는 0보다 커야됩니다."));

        this.preCourseSeqList = preCourseSeqList;
    }
}
