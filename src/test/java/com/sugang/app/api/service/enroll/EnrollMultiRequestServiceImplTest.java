package com.sugang.app.api.service.enroll;

import com.sugang.app.TestContainerIntegrationTestSupport;
import com.sugang.app.domain.course.Course;
import com.sugang.app.domain.course.CourseRepository;
import com.sugang.app.domain.course.CourseTestHelper;
import com.sugang.app.domain.course.infra.CreateCourseValidatorImpl;
import com.sugang.app.domain.student.Student;
import com.sugang.app.domain.student.StudentRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class EnrollMultiRequestServiceImplTest extends TestContainerIntegrationTestSupport {

    @Autowired
    EnrollServiceImpl enrollService;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    CreateCourseValidatorImpl createCourseValidator;

    @DisplayName("들어온 학생 수 만큼 강의 가능 학생 수가 줄어든다.")
    @Test
    void multiRequest() throws InterruptedException
    {
       // given
        Course course = Course.createCourse(
                CourseTestHelper.SCHEDULES,
                CourseTestHelper.SUMMARIES,
                CourseTestHelper.EXAMINATION,
                CourseTestHelper.PRE_COURSE_ID_SET,
                1L,
                1L,
                CourseTestHelper.COURSE_NAME,
                3,
                createCourseValidator,
                3
        );

        courseRepository.save(course);

        Student student1 = Student.createStudent(
                "Imac1",
                "e3@naver.com",
                1,
                17,
                1L,
                1L
        );

        Student student2 = Student.createStudent(
                "Imac2",
                "e2@naver.com",
                1,
                17,
                1L,
                1L
        );

        Student student3 = Student.createStudent(
                "Imac3",
                "e1@naver.com",
                1,
                17,
                1L,
                1L
        );

        List<Student> students = List.of(student3, student2, student1);
        studentRepository.saveAll(students);

        int threadNumberCount = students.size();

        ExecutorService executorService = Executors.newFixedThreadPool(threadNumberCount + 2);
        CountDownLatch latch = new CountDownLatch(threadNumberCount);

        // when
        for (int i = 0; i < students.size(); i++)
        {
            Student student = students.get(i);
            executorService.submit(() -> {
                try
                {
                    enrollService.enroll(course.getId(), student.getId());
                }
                catch (Exception e)
                {
                    e.printStackTrace();
                }
                finally
                {
                    latch.countDown();
                }
            });
        }

        latch.await();

        // then
        Course savedCourse = courseRepository.findById(course.getId()).orElseThrow();
        Assertions.assertThat(savedCourse.getAvailStudentCount()).isEqualTo(0);
    }
}
