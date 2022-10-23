package com.sugang.toys.command;

import org.junit.ClassRule;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;
import org.testcontainers.containers.DockerComposeContainer;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;

@Testcontainers
@SpringBootTest
@Transactional
@ActiveProfiles("test")
public class TestContainerConfiguration {

    @ClassRule
    static DockerComposeContainer dockerComposeContainer = new DockerComposeContainer(new File("src/main/resources/docker-compose.yml"));

}
