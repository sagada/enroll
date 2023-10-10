package com.sugang.app.api.service.course;

import com.sugang.app.api.service.course.request.CourseCreateServiceRequest;
import com.sugang.app.api.controller.course.dto.response.CreatedCourseResponse;
import com.sugang.app.domain.course.Course;
import com.sugang.app.domain.course.CourseName;
import com.sugang.app.domain.course.CourseRepository;
import com.sugang.app.domain.course.validator.CreateCourseValidator;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Transactional
@Service
public class CreateCourseService {

    private final CourseRepository courseRepository;
    private final CreateCourseValidator createCourseValidator;

    public CreatedCourseResponse createCourse(CourseCreateServiceRequest command)
    {
        Course course = Course.createCourse(
                command.convertCourseSchedules()
                , command.convertCourseSummary()
                , command.convertExamination()
                , command.preCourseIdSet()
                , command.professorId()
                , command.subjectId()
                , new CourseName(command.courseName())
                , command.score()
                , createCourseValidator
                , command.availableStudentCount()
        );

        courseRepository.save(course);
        return CreatedCourseResponse.from(course);
    }
}

