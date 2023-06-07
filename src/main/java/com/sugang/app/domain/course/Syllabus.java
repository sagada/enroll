package com.sugang.app.domain.course;

import com.sugang.app.global.exception.ErrorCode;
import com.sugang.app.domain.course.exception.CourseException;
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
public class Syllabus {

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(
            name = "course_summary"
            , joinColumns = @JoinColumn(name="course_id")
    )
    private Set<CourseSummary> courseSummaries;

    public Syllabus(Set<CourseSummary> courseSummaries)
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
