package com.sugang.toys.config;

import org.junit.ClassRule;
import org.springframework.boot.test.context.SpringBootTest;
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
@Testcontainers
public class TestContainerConfiguration{

    @ClassRule
    public static DockerComposeContainer dockerComposeContainer
            = new DockerComposeContainer(new File("src/test/resources/docker-compose.yml"));

    {
        dockerComposeContainer.start();
    }
}



