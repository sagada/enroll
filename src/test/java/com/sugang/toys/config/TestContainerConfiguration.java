package com.sugang.toys.config;

import org.junit.ClassRule;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers
public class TestContainerConfiguration extends ApplicationIntegrationTestConfiguration{

    @ClassRule
    static DockerComposeContainer dockerComposeContainer = new DockerComposeContainer(new File("src/test/resources/docker-compose.yml"));

    {
        dockerComposeContainer.start();
    }
}



