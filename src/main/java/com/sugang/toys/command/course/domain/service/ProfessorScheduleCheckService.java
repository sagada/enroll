package com.sugang.toys.command.course.domain.service;

import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.CourseSchedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
public class ProfessorScheduleCheckService {

    private final CourseRepository courseRepository;
    private final CourseScheduleOverlapCheckService courseScheduleOverlapCheckService;

    @Autowired
    public ProfessorScheduleCheckService(
            CourseRepository courseRepository
            , CourseScheduleOverlapCheckService courseScheduleOverlapCheckService)
    {
        this.courseRepository = courseRepository;
        this.courseScheduleOverlapCheckService = courseScheduleOverlapCheckService;
    }

    public void professorScheduleCheck(Long professorId, Set<CourseSchedule> openCourseScheduleList)
    {
        Set<CourseSchedule> courseScheduleList = courseRepository.findByProfessorId(professorId).stream()
                .flatMap(professorCourse -> professorCourse.getCourseSchedules().getCourseScheduleSet().stream())
                .collect(Collectors.toSet());

        checkOverlapOpenScheduleSet(openCourseScheduleList, courseScheduleList);
    }

    private void checkOverlapOpenScheduleSet(
            Set<CourseSchedule> openCourseScheduleList
            , Set<CourseSchedule> courseScheduleList)
    {
        if (courseScheduleOverlapCheckService.isOverlap(openCourseScheduleList, courseScheduleList))
        {
            throw new RuntimeException("중복되는 시간표가 있습니다!");
        }
    }
}
