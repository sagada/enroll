package com.sugang.toys.command.course.application;

import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.course.domain.CourseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CloseCourseService {

    private final CourseRepository courseRepository;

    public void close(Long courseId)
    {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("없는 수업"));
        // TODO 이메일 발송
        course.close();
    }
}
