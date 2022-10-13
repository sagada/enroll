package com.sugang.toys.command.config.db;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
        entityManagerFactoryRef = "masterEntityManager",
        transactionManagerRef = "masterTransactionManager"
)
public class MasterJpaDatasourceConfiguration {

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean masterEntityManager()
    {
        LocalContainerEntityManagerFactoryBean masterFactoryBean = new LocalContainerEntityManagerFactoryBean();
        masterFactoryBean.setDataSource(masterDataSource());
        masterFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        masterFactoryBean.setPackagesToScan(
                new String[]{"com.sugang.toys.command"});
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("hibernate.dialect", "org.hibernate.dialect.MySQL5InnoDBDialect");
        properties.put("hibernate.hbm2ddl.auto", "create");
        masterFactoryBean.setJpaPropertyMap(properties);
        return masterFactoryBean;
    }

    @Bean(name = "masterDbSource")
    @ConfigurationProperties(prefix = "spring.datasource.master")
    @Primary
    public DataSource masterDataSource()
    {
        return DataSourceBuilder.create().url("jdbc:mysql://localhost:3306/enroll").driverClassName("com.mysql.cj.jdbc.Driver").build();
    }

    @Primary
    @Bean
    public PlatformTransactionManager masterTransactionManager()
    {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(masterEntityManager().getObject());
        return transactionManager;
    }

}
