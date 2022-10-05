package com.sugang.toys.command.professor.infra;

import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.CourseSchedule;
import com.sugang.toys.command.professor.domain.OpenCourseScheduleValidator;
import com.sugang.toys.command.professor.domain.Professor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.function.Predicate;

@RequiredArgsConstructor
@Service
public class OpenCourseScheduleValidateService implements OpenCourseScheduleValidator {

    private final CourseRepository courseRepository;

    public void openCourseScheduleCheck(Professor professor, Set<CourseSchedule> openCourseScheduleList)
    {
        courseRepository.findByProfessorId(professor.getId())
                .stream()
                .flatMap(course -> course.getCourseSchedules().getCourseScheduleList().stream())
                .filter(Predicate.not(courseSchedule -> courseSchedule.contain(openCourseScheduleList)))
                .findAny()
                .orElseThrow(() -> new IllegalStateException("중복되는 시간표가 있습니다!"));
    }
}
