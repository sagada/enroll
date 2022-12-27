package com.sugang.toys.command.course.domain;

import com.sugang.toys.command.enroll.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByProfessorId(Long professorId);

    @Query("SELECT c.name from Course c where c.name.value = :courseName")
    String existsByName(@Param("courseName") String courseName);

    @Query(nativeQuery = true, value = "select course_id from enrollment e where student_id = :studentId")
    List<Enrollment> findEnrollmentListByStudentId(@Param("studentId") Long studentId);

    @Query("select c from Course c where c.id IN :studentCourseIdList and c.semester = :semester")
    List<Course> findAllByIds(List<Long> studentCourseIdList, String semester);
}
