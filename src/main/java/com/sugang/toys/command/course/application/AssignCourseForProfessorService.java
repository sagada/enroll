package com.sugang.toys.command.course.application;

import com.sugang.toys.command.common.exception.ErrorCode;
import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.CreateCourseValidator;
import com.sugang.toys.command.course.domain.exception.CourseException;
import com.sugang.toys.command.professor.domain.Professor;
import com.sugang.toys.command.professor.domain.ProfessorRepository;
import com.sugang.toys.command.professor.domain.exception.ProfessorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AssignCourseForProfessorService {

    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;
    private final CreateCourseValidator createCourseValidator;

    @Autowired
    public AssignCourseForProfessorService(
            CourseRepository courseRepository
            , ProfessorRepository professorRepository, CreateCourseValidator createCourseValidator)
    {
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
        this.createCourseValidator = createCourseValidator;
    }

    @Transactional
    public void assign(Long courseId, Long professorId)
    {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseException(ErrorCode.NONE_COURSE));

        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new ProfessorException(ErrorCode.NONE_PROFESSOR));


    }
}
