package com.sugang.toys.command.enroll.application;

import com.sugang.toys.command.enroll.domain.CourseEnroll;
import com.sugang.toys.command.enroll.domain.CourseEnrollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CourseEnrollService {
    private final CourseEnrollRepository courseEnrollRepository;

    @Autowired
    public CourseEnrollService(CourseEnrollRepository courseEnrollRepository)
    {
        this.courseEnrollRepository = courseEnrollRepository;
    }

    @Transactional
    public void register()
    {
        CourseEnroll courseEnroll = new CourseEnroll();
        courseEnrollRepository.save(courseEnroll);
    }
}
