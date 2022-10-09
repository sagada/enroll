package com.sugang.toys.command.course.infra;

import com.sugang.toys.command.course.domain.CourseRepository;
import com.sugang.toys.command.course.domain.CourseSchedule;
import com.sugang.toys.command.course.domain.CreateCourseValidator;
import com.sugang.toys.command.professor.domain.Professor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CreateCourseValidatorImpl implements CreateCourseValidator {

    private final CourseRepository courseRepository;

    public void validate(
            String courseName
            , Professor professor
            , Set<CourseSchedule> openCourseScheduleList
    )
    {
        if (openCourseScheduleList.isEmpty())
        {
            throw new RuntimeException("스케줄이 비었습니다.");
        }

        duplicateCourseName(courseName);
        professorScheduleCheck(professor, openCourseScheduleList);
    }

    private void professorScheduleCheck(Professor professor, Set<CourseSchedule> openCourseScheduleList)
    {
        List<CourseSchedule> courseScheduleList = courseRepository.findByProfessorId(professor.getId()).stream()
                .flatMap(professorCourse -> professorCourse.getCourseSchedules().getCourseScheduleSet().stream())
                .collect(Collectors.toList());

        for (CourseSchedule courseSchedule : courseScheduleList)
        {
            if (courseSchedule.contain(openCourseScheduleList))
            {
                throw new RuntimeException("중복되는 시간표가 있습니다!");
            }
        }
    }

    private void duplicateCourseName(String courseName)
    {
        if (courseRepository.existsByName(courseName))
        {
            throw new IllegalStateException("중복되는 수업 이름이 있습니다.");
        }
    }
}
