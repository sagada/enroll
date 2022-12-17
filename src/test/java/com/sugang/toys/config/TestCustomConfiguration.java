package com.sugang.toys.config;

import com.sugang.toys.config.db.DataSourceProperty;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@TestConfiguration
public class TestCustomConfiguration {

    private final DataSourceProperty dataSourceProperty;

    public TestCustomConfiguration(DataSourceProperty dataSourceProperty)
    {
        this.dataSourceProperty = dataSourceProperty;
    }

    @Bean
    public DataSource dataSource()
    {
        return DataSourceBuilder.create()
                .driverClassName(dataSourceProperty.getDriverClassName())
                .url(dataSourceProperty.getUrl())
                .username(dataSourceProperty.getUsername())
                .password(dataSourceProperty.getPassword())
                .type(HikariDataSource.class)
                .build();
    }
}
