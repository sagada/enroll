package com.sugang.toys.command.course.domain;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, Long> {
    List<Course> findByProfessorId(Long professorId);
    boolean existsByCourseName(CourseName courseName);
}
