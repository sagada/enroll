package com.sugang.toys.command.enroll.domain;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    @Query("select e from Enrollment e where e.student.id = :studentId")
    List<Enrollment> findEnrollmentListByStudentId(@Param("studentId") Long studentId);
}
