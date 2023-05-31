package com.sugang.app.domain.course;

import com.google.common.collect.Sets;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

public class CourseTestHelper {

    public static Set<Long> PRE_COURSE_ID_SET = Sets.newHashSet(1L, 2L);
    public static CourseName COURSE_NAME = new CourseName("originCourseName");
    public static Set<CourseSchedule> SCHEDULES = givenCourseSchedules();
    public static Set<CourseSummary> SUMMARIES = givenCourseSummaries();
    public static CourseExamination EXAMINATION = givenCourseExamination();

    // UPDATED
    public static Set<CourseSummary> UPDATED_SUMMARIES = getUpdatedSummaries();
    public static CourseExamination UPDATED_EXAMINATION = getUpdatedCourseExamination();
    public static CourseName UPDATED_COURSE_NAME = new CourseName("updatedCourseName");

    static Set<CourseSummary> givenCourseSummaries()
    {
        return Sets.newHashSet(
                new CourseSummary(1, "content", "title")
        );
    }

    static CourseExamination givenCourseExamination()
    {
        return new CourseExamination(
                LocalDateTime.of(2022, Month.JANUARY, 13, 13, 15),
                LocalDateTime.of(2022, Month.SEPTEMBER, 13, 14, 15)
        );
    }

    static Set<CourseSchedule> givenCourseSchedules()
    {
        return Sets.newHashSet(
                new CourseSchedule(
                        LocalDateTime.of(2022, Month.MARCH, 1, 13, 30),
                        LocalDateTime.of(2022, Month.MARCH, 1, 14, 30),
                        "2004"
                )
        );
    }

    static Set<CourseSummary> getUpdatedSummaries()
    {
        return Sets.newHashSet(
                new CourseSummary(1, "updatedContent1", "updatedTitle1"),
                new CourseSummary(2, "updatedContent2", "updatedTitle2"),
                new CourseSummary(3, "updatedContent3", "updatedTitle3")
        );
    }

    static CourseExamination getUpdatedCourseExamination()
    {
        return new CourseExamination(
                LocalDateTime.of(2022, Month.JANUARY, 13, 13, 15),
                LocalDateTime.of(2022, Month.SEPTEMBER, 13, 15, 15)
        );
    }

}
