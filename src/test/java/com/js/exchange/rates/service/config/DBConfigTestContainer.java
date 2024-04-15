package com.js.exchange.rates.service.config;

import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.testcontainers.containers.MSSQLServerContainer;
import org.testcontainers.containers.output.Slf4jLogConsumer;

import javax.sql.DataSource;

@Configuration
@Slf4j
@Profile("dev")
@MapperScan("com.js.exchange.rates.service.mapper")
@PropertySource({"classpath:application.properties", "classpath:application-dev.properties"})
public class DBConfigTestContainer {

    @Bean
    public MSSQLServerContainer<?> mssqlServerContainer() {
        var mssqlServerContainer = new MSSQLServerContainer<>("mcr.microsoft.com/mssql/server:2017-CU12");
        mssqlServerContainer.withInitScript("deploy/database.sql");
        mssqlServerContainer.start();
        mssqlServerContainer.followOutput(new Slf4jLogConsumer(log));
        return mssqlServerContainer;
    }

    @Bean("exchangeRatesServiceDataSource")
    public DataSource dataSource(MSSQLServerContainer<?> mssqlServerContainer) {
        return DataSourceBuilder.create()
                .driverClassName(mssqlServerContainer.getDriverClassName())
                .url(mssqlServerContainer.getJdbcUrl())
                .username(mssqlServerContainer.getUsername())
                .password(mssqlServerContainer.getPassword())
                .build();
    }
}
