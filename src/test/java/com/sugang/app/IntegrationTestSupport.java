package com.sugang.app;

import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import javax.transaction.Transactional;

@Transactional
@ActiveProfiles("test")
@SpringBootTest
public class IntegrationTestSupport {
}
