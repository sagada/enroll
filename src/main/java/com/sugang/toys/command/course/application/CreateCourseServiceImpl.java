package com.sugang.toys.command.course.application;

import com.sugang.toys.command.course.application.dto.CourseCreateCommand;
import com.sugang.toys.command.course.application.dto.CreatedCourseResult;
import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.course.domain.CourseName;
import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.validator.CreateCourseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateCourseServiceImpl implements CreateCourseService{

    private final CourseRepository courseRepository;
    private final CreateCourseValidator createCourseValidator;

    public CreatedCourseResult createCourse(CourseCreateCommand command)
    {
        Course course = Course.createCourse(
                command.convertCourseSchedules()
                , command.convertCourseSummary()
                , command.convertExamination()
                , command.getPreCourseIdSet()
                , command.getProfessorId()
                , command.getSubjectId()
                , new CourseName(command.getCourseName())
                , command.getScore()
                , createCourseValidator
        );

        courseRepository.save(course);
        return CreatedCourseResult.from(course);
    }
}
