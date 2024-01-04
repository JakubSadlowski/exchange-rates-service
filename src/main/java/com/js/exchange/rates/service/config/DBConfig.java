package com.js.exchange.rates.service.config;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.sql.DataSource;

@Configuration
@MapperScan("com.js.exchange.rates.service.mapper")
public class DBConfig {
    @Value("${db.url}")
    private String url;
    @Value("${db.applicationname}")
    private String applicationName;
    @Value("${db.login}")
    private String login;
    @Value("${db.password}")
    private String password;
    @Bean
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

    /*@Bean
    public SqlSessionFactory sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(createDataSource());
        return factoryBean.getObject();
    }*/

    @Bean
    public SqlSessionFactory sqlSessionFactory() throws IllegalStateException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(createDataSource());
        try {
            return sqlSessionFactoryBean.getObject();
        } catch (Exception e) {
            throw new IllegalStateException("Unable to create sqlSessionFactory ", e);
        }
    }
}
