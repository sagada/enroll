package com.sugang.app.api.controller.course;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sugang.app.api.controller.course.dto.request.CourseCreateRequest;
import com.sugang.app.api.controller.course.dto.request.CourseScheduleRequest;
import com.sugang.app.api.controller.course.dto.request.CourseSummaryRequest;
import com.sugang.app.api.controller.course.dto.response.CreatedCourseResponse;
import com.sugang.app.api.service.course.CreateCourseServiceImpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;
import java.util.Set;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
@WebMvcTest(CourseController.class)
class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CreateCourseServiceImpl createCourseService;

    @Autowired
    ObjectMapper objectMapper;

    public static CourseCreateRequest given()
    {
        return CourseCreateRequest.builder()
                .courseName("새로운 이름의 수업")
                .bookName("책이름")
                .score(3)
                .preCourseIdSet(Set.of(1L, 2L, 3L))
                .professorId(1L)
                .subjectId(1L)
                .courseSummaryRequestSet(Set.of(
                                CourseSummaryRequest.builder()
                                        .content("content")
                                        .title("title")
                                        .week(1)
                                        .build()
                        )
                )
                .courseScheduleRequestSet(Set.of(
                                CourseScheduleRequest.builder()
                                        .from(LocalDateTime.of(2023,4,5,12,30))
                                        .to(LocalDateTime.of(2023,4,5,14,50))
                                        .roomNumber("1004")
                                        .build()
                        )
                )
                .build();
    }

    @DisplayName("수업을 생성한다.")
    @Test
    void createCourse() throws Exception
    {
        // given
        CourseCreateRequest request = given();
        CreatedCourseResponse createdCourseResponse = new CreatedCourseResponse();
        BDDMockito.given(createCourseService.createCourse(Mockito.any())).willReturn(createdCourseResponse);

        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/course")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                // then
                .andExpect(status().isOk());
    }

    @DisplayName("수업을 생성 시 과목 ID는 필수값이다.")
    @Test
    void createCourseWithoutCourseName() throws Exception
    {
        // given
        CourseCreateRequest request = given()
                .setCourseName("");

        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/course")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                // then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("[courseName] - 수업 이름은 필수 값입니다."));
    }

    @DisplayName("수업을 생성 시 수업이름은 필수값이다.")
    @Test
    void createCourseWithoutSubjectId() throws Exception
    {
        // given
        CourseCreateRequest request = given()
                .setSubjectId(null);

        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/course")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                // then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("[subjectId] - 과목 ID는 필수 값입니다."));
    }

    @DisplayName("수업을 생성 시 학점은 1이상 이어야한다.")
    @Test
    void createCourseWithZeroScore() throws Exception
    {
        // given
        CourseCreateRequest request = given()
                .setScore(0);

        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/course")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                // then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("[score] - 학점은 1점 이상입니다."));
    }

    @DisplayName("수업을 생성 시 학점은 5이하여하 한다.")
    @Test
    void createCourseWithOverScore() throws Exception
    {
        // given
        CourseCreateRequest request = given()
                .setScore(6);

        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/course")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                // then
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message")
                        .value("[score] - 학점은 5점 이하입니다."));
    }
}