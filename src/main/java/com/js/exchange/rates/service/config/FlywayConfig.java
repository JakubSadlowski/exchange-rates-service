package com.js.exchange.rates.service.config;

import org.flywaydb.core.Flyway;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.flyway.FlywayProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.util.StringUtils;

import javax.sql.DataSource;

import static org.springframework.util.StringUtils.toStringArray;

@Configuration
@EnableConfigurationProperties(FlywayProperties.class)
public class FlywayConfig {
    @Bean
    public Flyway flyway(@Qualifier("exchangeRatesServiceDataSource") DataSource dataSource, FlywayProperties flywayProperties) {
        Flyway flyway = Flyway.configure()
                .dataSource(dataSource)
                .table(flywayProperties.getTable())
                .baselineOnMigrate(flywayProperties.isBaselineOnMigrate())
                .schemas(toStringArray(flywayProperties.getSchemas()))
                .locations(toStringArray(flywayProperties.getLocations()))
                .placeholders(flywayProperties.getPlaceholders())
                .placeholderReplacement(flywayProperties.isPlaceholderReplacement())
                //.initSql("use service_adr_expiration;")
                .load();
        flyway.repair();
        flyway.migrate();
        return flyway;
    }
}
