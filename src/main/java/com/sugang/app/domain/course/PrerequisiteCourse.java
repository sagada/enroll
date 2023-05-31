package com.sugang.app.domain.course;

import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Set;

@Getter
@NoArgsConstructor
@Embeddable
public class PrerequisiteCourse {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "pre_course", joinColumns = @JoinColumn(name = "course_id"))
    private Set<Long> preCourseSeqList;

    public PrerequisiteCourse(Set<Long> preCourseSeqList)
    {
        if (preCourseSeqList == null)
        {
            return ;
        }

        this.preCourseSeqList = preCourseSeqList;
    }
}
