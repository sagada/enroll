package com.sugang.toys.command.professor.application;

import com.sugang.toys.command.course.application.CloseCourseService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/course")
public class CourseController {

    private final ProfessorOpenCourseService professorOpenCourseService;
    private final CloseCourseService closeCourseService;
    @PostMapping("/open")
    public ResponseEntity<Long> openCourse(@RequestBody ProfessorOpenCourseRequest professorOpenCourseRequest)
    {
        return ResponseEntity.ok(professorOpenCourseService.professorOpenCourse(professorOpenCourseRequest));
    }

    @PostMapping("/close/{courseId}")
    public ResponseEntity<Void> closeCourse(@PathVariable Long courseId)
    {
        closeCourseService.close(courseId);
        return ResponseEntity.ok(null);
    }
}
