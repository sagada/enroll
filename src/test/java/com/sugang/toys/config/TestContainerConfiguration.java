package com.sugang.toys.config;

import org.junit.ClassRule;
import org.springframework.context.annotation.Import;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Import(value = TestCustomConfiguration.class)
@Testcontainers
public class TestContainerConfiguration{

    @ClassRule
    public static DockerComposeContainer dockerComposeContainer = new DockerComposeContainer(new File("src/test/resources/docker-compose.yml"));

    static
    {
        dockerComposeContainer.start();
    }
}



