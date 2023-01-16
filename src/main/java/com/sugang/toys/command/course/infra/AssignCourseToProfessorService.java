package com.sugang.toys.command.course.infra;

import com.sugang.toys.command.common.exception.ErrorCode;
import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.ProfessorCourseValidator;
import com.sugang.toys.command.course.domain.exception.CourseException;
import com.sugang.toys.command.professor.domain.Professor;
import com.sugang.toys.command.professor.domain.ProfessorRepository;
import com.sugang.toys.command.professor.domain.exception.ProfessorException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@RequiredArgsConstructor
@Service
public class AssignCourseToProfessorService {

    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;
    private final ProfessorCourseValidator professorCourseValidator;

    public void assignCourseToProfessor(Long courseId, Long professorId)
    {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseException(ErrorCode.NONE_COURSE));

        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new ProfessorException(ErrorCode.NONE_PROFESSOR));

        course.assign(professorCourseValidator, professor);
    }
}
