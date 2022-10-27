package com.sugang.toys.command.course.application;

import com.sugang.toys.command.common.exception.ErrorCode;
import com.sugang.toys.command.course.domain.Course;
import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.exception.CourseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OpenCourseService {

    private final CourseRepository courseRepository;
    private final KafkaProducerService kafkaProducerService;

    @Autowired
    public OpenCourseService(
            CourseRepository courseRepository
            , KafkaProducerService kafkaProducerService)
    {
        this.courseRepository = courseRepository;
        this.kafkaProducerService = kafkaProducerService;
    }

    public void openCourse(Long courseId)
    {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new CourseException(ErrorCode.NONE_COURSE));

        kafkaProducerService.send(course);
        course.open();
    }
}
