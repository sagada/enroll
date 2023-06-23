package com.sugang.app;

import com.sugang.app.global.TestCustomConfiguration;
import org.junit.ClassRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.containers.wait.strategy.Wait;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.time.Duration;

@SpringBootTest
@ContextConfiguration(classes = TestCustomConfiguration.class)
@TestPropertySource(locations = "classpath:application-integration.yml")
@ActiveProfiles("integration")
@Testcontainers
public class TestContainerIntegrationTestSupport {

    @ClassRule
    public static DockerComposeContainer<?> dockerComposeContainer = new DockerComposeContainer<>(new File("src/test/resources/docker-compose.yml"))
            .withExposedService("broker_1", 9092, Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(30)))
            .withExposedService("zookeeper_1", 2181, Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(30)));

    static {
        dockerComposeContainer.start()
        ;
    }
}



