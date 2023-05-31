package com.sugang.app.domain.course;

import com.google.common.collect.Sets;
import com.sugang.app.global.JpaRepositoryTestConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("코스 레파지토리 테스트")
public class CourseRepositoryTest extends JpaRepositoryTestConfiguration {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EntityManager entityManager;

    @DisplayName("Course 생성 테스트")
    @Test
    void create()
    {
        // given
        Course course = new Course(
                CourseTestHelper.SCHEDULES,
                CourseTestHelper.EXAMINATION,
                CourseTestHelper.SUMMARIES,
                CourseTestHelper.PRE_COURSE_ID_SET,
                CourseTestHelper.COURSE_NAME,
                CourseStatus.OPEN,
                2,
                1L,
                1L
        );

        // when
        Course savedCourse = courseRepository.save(course);

        // then
        assertThat(savedCourse).isNotNull();
        assertThat(savedCourse.getCourseStatus()).isEqualTo(CourseStatus.OPEN);
        assertThat(savedCourse.getCourseName().getValue()).isEqualTo(CourseTestHelper.COURSE_NAME.getValue());
        assertThat(savedCourse.getScore()).isEqualTo(2);
        assertThat(savedCourse.getPrerequisiteCourse()).extracting(PrerequisiteCourse::getPreCourseSeqList)
                .isEqualTo(Set.of(1L, 2L));

        Syllabus savedCourseSyllabus = savedCourse.getSyllabus();
        assertThat(savedCourseSyllabus.getCourseSummaries()).hasSize(1);
        assertThat(savedCourseSyllabus.getCourseSummaries())
                .isEqualTo(
                        Set.of(
                                new CourseSummary(1, "content", "title")
                        )
                );

        CourseSchedules savedCourseSchedules = savedCourse.getCourseSchedules();
        assertThat(savedCourseSchedules.getCourseScheduleSet()).hasSize(1);
        assertThat(savedCourseSchedules.getCourseScheduleSet()).isEqualTo(Sets.newHashSet(
                new CourseSchedule(
                        LocalDateTime.of(2022, Month.MARCH, 1, 13, 30),
                        LocalDateTime.of(2022, Month.MARCH, 1, 14, 30),
                        "2004"
                )
        ));
    }

    @Test
    @DisplayName("course close 테스트")
    void close()
    {
        // given
        Course course = new Course(
                CourseTestHelper.SCHEDULES,
                CourseTestHelper.EXAMINATION,
                CourseTestHelper.SUMMARIES,
                CourseTestHelper.PRE_COURSE_ID_SET,
                CourseTestHelper.COURSE_NAME,
                CourseStatus.OPEN,
                2,
                1L,
                1L
        );

        Course savedCourse = courseRepository.save(course);

        // when
        savedCourse.close();
        entityManager.flush();
        entityManager.clear();

        Course findCourse = courseRepository.findById(savedCourse.getId()).orElseThrow();

        // then
        assertThat(findCourse.getCourseStatus()).isEqualTo(CourseStatus.CLOSE);
    }
}
