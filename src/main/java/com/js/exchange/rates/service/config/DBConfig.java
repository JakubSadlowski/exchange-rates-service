package com.js.exchange.rates.service.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

import javax.sql.DataSource;

@Configuration
@Profile("!dev")
@MapperScan("com.js.exchange.rates.service.mapper")
@PropertySource({"classpath:application.properties", "classpath:application-dev.properties"})
public class DBConfig {
    @Value("${db.url}")
    private String url;
    @Value("${db.applicationname}")
    private String applicationName;
    @Value("${db.login}")
    private String login;
    @Value("${db.password}")
    private String password;

    @Bean("exchangeRatesServiceDataSource")
    public DataSource createDataSource() {
        BasicDataSource dataSource = new BasicDataSource();

        dataSource.setUrl(url);
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUsername(login);
        dataSource.setPassword(password);
        dataSource.setMaxTotal(-1);
        dataSource.setMaxIdle(-1);
        dataSource.setMinIdle(3);
        dataSource.setMinEvictableIdleTimeMillis(900000);
        dataSource.setTimeBetweenEvictionRunsMillis(1800000);
        dataSource.setTestWhileIdle(true);
        dataSource.setValidationQueryTimeout(5);
        dataSource.setValidationQuery("SELECT 1");
        return dataSource;
    }
}
