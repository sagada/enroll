package com.sugang.app.domain.course.application;

import com.sugang.app.domain.course.application.dto.CourseCreateCommand;
import com.sugang.app.domain.course.application.dto.CreatedCourseResult;
import com.sugang.app.domain.course.domain.Course;
import com.sugang.app.domain.course.domain.CourseName;
import com.sugang.app.domain.course.domain.CourseRepository;
import com.sugang.app.domain.course.domain.validator.CreateCourseValidator;
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
