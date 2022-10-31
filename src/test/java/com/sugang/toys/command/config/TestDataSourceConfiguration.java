package com.sugang.toys.command.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ActiveProfiles;

import javax.sql.DataSource;

@ActiveProfiles("test")
@Configuration
public class TestDataSourceConfiguration {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource")
    public DataSource dataSource()
    {
        return DataSourceBuilder.create()
                .type(HikariDataSource.class)
                .build();
    }
}
