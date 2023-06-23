package com.sugang.app.api.controller.enroll;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sugang.app.api.controller.enroll.dto.request.EnrollRequest;
import com.sugang.app.api.service.enroll.EnrollService;
import com.sugang.app.api.service.enroll.response.EnrollServiceResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDateTime;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@DisplayName("수강신청 컨트롤러 테스트")
@ExtendWith(MockitoExtension.class)
@WebMvcTest(EnrollmentController.class)
class EnrollmentControllerTest {

    @MockBean
    EnrollService enrollService;

    @Autowired
    MockMvc mockMvc;

    @Autowired
    ObjectMapper objectMapper;

    @DisplayName("강의를 수강 등록할 수 있다.")
    @Test
    void enroll() throws Exception
    {
        // given
        LocalDateTime time = LocalDateTime.of(2023, 4, 15, 19, 15);

        EnrollServiceResponse response = EnrollServiceResponse.builder()
                .enrollId(1L)
                .courseId(1L)
                .studentId(1L)
                .courseName("운영체제")
                .studentName("홍길동")
                .registerDateTime(time)
                .build();

        Mockito.when(enrollService.enroll(Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(response);

        EnrollRequest request = EnrollRequest.builder()
                .courseId(1L)
                .studentId(1L)
                .build();
        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/enroll")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                // then
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.courseId").value("1"))
                .andExpect(jsonPath("$.studentId").value("1"))
                .andExpect(jsonPath("$.enrollId").value("1"))
                .andExpect(jsonPath("$.courseName").value("운영체제"))
                .andExpect(jsonPath("$.studentName").value("홍길동"))
                .andExpect(jsonPath("$.registerDateTime").value("2023-04-15 19:15:00"));
    }

    @DisplayName("course Id 는 필수다.")
    @Test
    void courseIdEmpty() throws Exception
    {
        // given
        LocalDateTime time = LocalDateTime.of(2023, 4, 15, 19, 15);

        EnrollServiceResponse response = EnrollServiceResponse.builder()
                .enrollId(1L)
                .courseId(1L)
                .studentId(1L)
                .courseName("운영체제")
                .studentName("홍길동")
                .registerDateTime(time)
                .build();

        Mockito.when(enrollService.enroll(Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(response);

        EnrollRequest request = EnrollRequest.builder()
                .studentId(1L)
                .build();
        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/enroll")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[courseId] - 수업 ID는 필수입니다."));
    }

    @DisplayName("student Id 는 필수다.")
    @Test
    void studentIdEmpty() throws Exception
    {
        // given
        LocalDateTime time = LocalDateTime.of(2023, 4, 15, 19, 15);

        EnrollServiceResponse response = EnrollServiceResponse.builder()
                .enrollId(1L)
                .courseId(1L)
                .studentId(1L)
                .courseName("운영체제")
                .studentName("홍길동")
                .registerDateTime(time)
                .build();

        Mockito.when(enrollService.enroll(Mockito.anyLong(), Mockito.anyLong()))
                .thenReturn(response);

        EnrollRequest request = EnrollRequest.builder()
                .courseId(1L)
                .build();
        // when
        mockMvc.perform(MockMvcRequestBuilders.post("/api/enroll")
                        .content(objectMapper.writeValueAsString(request))
                        .contentType(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.message").value("[studentId] - 학생 ID는 필수입니다."));
    }
}