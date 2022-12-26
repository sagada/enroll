package com.sugang.toys.command.course.application;

import com.sugang.toys.command.course.application.dto.CourseCreateCommand;
import com.sugang.toys.command.course.application.dto.CreatedCourseResult;
import com.sugang.toys.command.course.domain.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateCourseServiceImpl implements CreateCourseService{

    private final CourseRepository courseRepository;
    private final CreateCourseValidator createCourseValidator;

    public CreatedCourseResult createCourse(CourseCreateCommand command)
    {
        Set<CourseSchedule> openCourseScheduleSet = command.convertRequestIntoCourseSchedules();
        Set<CourseSummary> courseSummarySet = command.convertRequestIntoCourseSummary();

        Course course = Course.createCourse(
                openCourseScheduleSet
                , courseSummarySet
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
