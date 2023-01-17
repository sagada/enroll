package com.sugang.toys.command.course.domain.validator;

import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.professor.domain.Professor;

public interface ProfessorCourseValidator {
    void validate(Course course, Professor professor);
}
