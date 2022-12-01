package com.sugang.toys.config;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;

/**
 * 통합 테스트 config 클래스
 */
@Transactional
@TestPropertySource(locations = "classpath:application-integration.yml")
@ContextConfiguration(classes = TestDataSourceConfiguration.class)
@SpringBootTest
@ActiveProfiles("integration")
public class ApplicationIntegrationTestConfiguration {
}
