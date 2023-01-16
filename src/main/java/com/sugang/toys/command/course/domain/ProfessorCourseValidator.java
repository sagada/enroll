package com.sugang.toys.command.course.domain;

import com.sugang.toys.command.professor.domain.Professor;

public interface ProfessorCourseValidator {
    void validate(Course course, Professor professor);
}
