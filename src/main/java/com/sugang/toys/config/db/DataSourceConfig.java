package com.sugang.toys.config.db;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Log4j2
@Configuration
public class DataSourceConfig {

    private final DataSourceProperty dataSourceProperty;

    public DataSourceConfig(DataSourceProperty dataSourceProperty)
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