package com.sugang.toys.command.course.infra;

import com.sugang.toys.command.course.domain.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DuplicateCourseNameCheck {
    private final CourseRepository courseRepository;

    @Autowired
    public DuplicateCourseNameCheck(CourseRepository courseRepository)
    {
        this.courseRepository = courseRepository;
    }

    public void duplicateCourseNameCheck(String courseName)
    {
        if (courseRepository.existsByName(courseName) != null)
        {
            throw new RuntimeException("중복되는 수업 이름이 있습니다.");
        }
    }
}
