package com.sugang.toys.command.course.infra;

import com.sugang.toys.command.common.exception.ErrorCode;
import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.CourseSchedule;
import com.sugang.toys.command.course.domain.exception.CourseException;
import com.sugang.toys.command.professor.domain.Professor;
import com.sugang.toys.command.professor.domain.ProfessorRepository;
import com.sugang.toys.command.professor.domain.exception.ProfessorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AssignCourseToProfessorService {

    private final CourseRepository courseRepository;
    private final ProfessorRepository professorRepository;

    @Autowired
    public AssignCourseToProfessorService(
            CourseRepository courseRepository
            , ProfessorRepository professorRepository)
    {
        this.courseRepository = courseRepository;
        this.professorRepository = professorRepository;
    }

    @Transactional
    public void assignCourseToProfessor(Long courseId, Long professorId)
    {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseException(ErrorCode.NONE_COURSE));

        Professor professor = professorRepository.findById(professorId)
                .orElseThrow(() -> new ProfessorException(ErrorCode.NONE_PROFESSOR));

        Set<CourseSchedule> professorCourseSchedule = courseRepository.findByProfessorId(professorId)
                .stream()
                .flatMap(professorCourse -> professorCourse.getCourseSchedules().getCourseScheduleSet().stream())
                .collect(Collectors.toSet());

        course.assign(professorCourseSchedule, professor);
    }
}
