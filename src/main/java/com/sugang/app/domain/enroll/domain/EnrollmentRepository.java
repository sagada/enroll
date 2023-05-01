package com.sugang.app.domain.enroll.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    @Query("select e.courseId from Enrollment e where e.enrollStudent.studentId = :studentId")
    List<Long> findEnrollmentListByStudentId(@Param("studentId") Long studentId);
}
