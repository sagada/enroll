package com.sugang.app.global;

import org.junit.ClassRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@SpringBootTest
@Transactional
@ContextConfiguration(classes = TestCustomConfiguration.class)
@TestPropertySource(locations = "classpath:application-integration.yml")
@ActiveProfiles("integration")
@Testcontainers
public class IntegrationTestConfiguration {

    @ClassRule
    public static DockerComposeContainer<?> dockerComposeContainer = new DockerComposeContainer<>(new File("src/test/resources/docker-compose.yml"));

    static {
        dockerComposeContainer.start();
    }
}



