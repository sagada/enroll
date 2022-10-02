package com.sugang.toys.command.professor.domain;

import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.CourseSchedule;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProfessorOpenCourseValidateService {

    private final CourseRepository courseRepository;

    public void validate(Professor professor, Set<CourseSchedule> courseScheduleList)
    {
        List<CourseSchedule> collect = courseRepository.findByProfessorId(professor.getId())
                .stream()
                .flatMap(course -> course.getCourseSchedules().getCourseScheduleList().stream())
                .collect(Collectors.toList());


    }
}
