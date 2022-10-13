package com.sugang.toys.command.config.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({"classpath:application-dev.yml"})
@EnableJpaRepositories(
        entityManagerFactoryRef = "slaveEntityManager",
        transactionManagerRef = "slaveTransactionManager"
)
public class SlaveJpaDataSourceConfiguration {

    @Bean
    public LocalContainerEntityManagerFactoryBean slaveEntityManager()
    {
        LocalContainerEntityManagerFactoryBean slaveFactoryBean = new LocalContainerEntityManagerFactoryBean();
        slaveFactoryBean.setDataSource(slaveDataSource());
        slaveFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        slaveFactoryBean.setPackagesToScan(
                new String[]{"com.sugang.toys.command"});
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.put("hibernate.hbm2ddl.auto", "create");
        slaveFactoryBean.setJpaPropertyMap(properties);
        return slaveFactoryBean;
    }

    @Bean(name = "slaveDbSource")
    @ConfigurationProperties(prefix = "spring.datasource.slave")
    public DataSource slaveDataSource()
    {
        return DataSourceBuilder.create()
                .url("jdbc:mysql://localhost:3307/enroll").driverClassName("com.mysql.cj.jdbc.Driver").build();
    }

    @Bean
    public PlatformTransactionManager slaveTransactionManager()
    {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(slaveEntityManager().getObject());
        return transactionManager;
    }
}
