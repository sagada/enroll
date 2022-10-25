package com.sugang.toys.command.course.domain;

import com.sugang.toys.command.common.exception.ErrorCode;
import com.sugang.toys.command.course.domain.exception.CourseException;
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
        validateCourseSummaries(courseSummaries);
        this.courseSummaries = courseSummaries;
    }

    private void validateCourseSummaries(Set<CourseSummary> courseSummaries)
    {
        if (CollectionUtils.isEmpty(courseSummaries))
        {
            throw new CourseException(ErrorCode.COURSE_SUMMARY_ERROR);
        }

        long count = courseSummaries.stream()
                .map(CourseSummary::getWeek)
                .distinct()
                .count();

        if (count != courseSummaries.size())
        {
            throw new CourseException("강좌 요약 중복된 week 존재");
        }
    }
}
