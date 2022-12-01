package com.sugang.toys.command.common.event;

import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.course.domain.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EnrollRegisteredEventListener {

    private final CourseRepository courseRepository;

    @Autowired
    public EnrollRegisteredEventListener(CourseRepository courseRepository)
    {
        this.courseRepository = courseRepository;
    }

    @EventListener(EnrollRegisteredEvent.class)
    public void listenCourse(final EnrollRegisteredEvent event)
    {
        Course course = courseRepository.findById(event.getCourseId())
                .orElseThrow(() -> new RuntimeException("Not Found Course"));
        course.addStudent();
    }
}
