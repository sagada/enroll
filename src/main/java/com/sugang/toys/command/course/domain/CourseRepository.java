package com.sugang.toys.command.course.domain;

import com.sugang.toys.command.enroll.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByProfessorId(Long professorId);
    boolean existsByName(CourseName courseName);

    // 학생 -> 수강 신청 <- 수강
    @Query(nativeQuery = true, value = "select course_id from enrollment e where student_id = :studentId")
    List<Enrollment> findEnrollmentListByStudentId(@Param("studentId") Long studentId);
}
