package com.sugang.app.domain.course;

import com.sugang.app.domain.course.exception.CourseException;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.util.CollectionUtils;

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
            throw new IllegalStateException("empty courseSummaries");
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
