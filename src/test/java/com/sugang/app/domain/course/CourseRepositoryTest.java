package com.sugang.app.domain.course;

import com.google.common.collect.Sets;
import com.sugang.app.JpaRepositoryTestConfiguration;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("수업 레파지토리 테스트")
public class CourseRepositoryTest extends JpaRepositoryTestConfiguration {

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    EntityManager entityManager;

    @DisplayName("수업을 생성 할 수 있다.")
    @Test
    void create()
    {
        // given
        Course course = new Course(
                Sets.newHashSet(
                        new CourseSchedule(
                                LocalDateTime.of(2022, 3, 1, 13, 30),
                                LocalDateTime.of(2022, 3, 1, 14, 30),
                                "2004"
                        )
                ),
                CourseTestHelper.EXAMINATION,
                CourseTestHelper.SUMMARIES,
                CourseTestHelper.PRE_COURSE_ID_SET,
                CourseTestHelper.COURSE_NAME,
                CourseStatus.OPEN,
                2,
                1L,
                1L,
                10
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

        assertThat(savedCourse.getCourseScheduleSet()).hasSize(1);
        assertThat(savedCourse.getCourseScheduleSet()).isEqualTo(Sets.newHashSet(
                new CourseSchedule(
                        LocalDateTime.of(2022, Month.MARCH, 1, 13, 30),
                        LocalDateTime.of(2022, Month.MARCH, 1, 14, 30),
                        "2004"
                )
        ));
        assertThat(savedCourse.getAvailStudentCount()).isEqualTo(10);
    }

    @Test
    @DisplayName("수업을 닫을 수 있다.")
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
                1L,
                10
        );

        Course savedCourse = courseRepository.save(course);

        // when
        savedCourse.close();
        Course findCourse = courseRepository.findById(savedCourse.getId()).orElseThrow();

        // then
        assertThat(findCourse.getCourseStatus()).isEqualTo(CourseStatus.CLOSE);
    }
}
