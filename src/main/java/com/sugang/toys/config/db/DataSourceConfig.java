package com.sugang.toys.config.db;

import com.zaxxer.hikari.HikariDataSource;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;


@Log4j2
@RequiredArgsConstructor
@Configuration
public class DataSourceConfig {

    private final DataSourceProperty dataSourceProperty;

    @Bean
    public DataSource dataSource()
    {
        log.info("dataSource Properties url : {}, username : {}"
                ,  dataSourceProperty.getUrl()
                , dataSourceProperty.getUsername());

        return DataSourceBuilder.create()
                .driverClassName(dataSourceProperty.getDriverClassName())
                .url(dataSourceProperty.getUrl())
                .username(dataSourceProperty.getUsername())
                .password(dataSourceProperty.getPassword())
                .type(HikariDataSource.class)
                .build();
    }
}