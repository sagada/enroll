package com.sugang.toys.command.course.application;

import com.sugang.toys.command.course.application.dto.CourseCreateCommand;
import com.sugang.toys.command.course.application.dto.CreatedCourseResult;
import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.CreateCourseValidator;
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
                , command.getProfessorId()
                , command.getCourseName()
                , command.getDepartmentId()
                , command.getBookName()
                , command.getScore()
                , createCourseValidator
        );

        Course save = courseRepository.save(course);
        return CreatedCourseResult.from(save);
    }
}
