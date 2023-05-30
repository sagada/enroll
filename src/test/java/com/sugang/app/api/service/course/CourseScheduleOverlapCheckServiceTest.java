package com.sugang.app.api.service.course;

import com.sugang.app.IntegrationTestSupport;
import com.sugang.app.domain.course.domain.service.CourseScheduleOverlapCheckService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

class CourseScheduleOverlapCheckServiceTest extends IntegrationTestSupport {

    @Autowired
    CourseScheduleOverlapCheckService courseScheduleOverlapCheckService;
    
    @DisplayName("")
    @Test
    void duplicateCourse()
    {
        // given
        
        // when
        
        // then
    }
}