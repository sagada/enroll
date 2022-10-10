package com.sugang.toys.command;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

@Testcontainers
@SpringBootTest
@ActiveProfiles("test")
public class TestContainerConfiguration {

    @Container
    static MySQLContainer mySQLContainer = new MySQLContainer("mysql:5.7.34")
            .withDatabaseName("enroll")
            .withUsername("root");

}
