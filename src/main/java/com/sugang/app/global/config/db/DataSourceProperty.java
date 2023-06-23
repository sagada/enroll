package com.sugang.app.global.config.db;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@Getter
@Setter
@ConfigurationProperties(prefix = "spring.datasource")
public class DataSourceProperty {
    private String url;
    private String driverClassName;
    private String username;
    private String password;
}