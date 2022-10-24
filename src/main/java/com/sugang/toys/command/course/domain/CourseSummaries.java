package com.sugang.toys.command.course.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import java.util.Set;

@Getter
@NoArgsConstructor
public class CourseSummaries {
    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "course_summary"
            , joinColumns = @JoinColumn(name="course_id")
    )
    private Set<CourseSummary> courseSummaries;

    public CourseSummaries(Set<CourseSummary> courseSummaries)
    {
        if (CollectionUtils.isEmpty(courseSummaries))
            return ;

        this.courseSummaries = courseSummaries;
    }
}
