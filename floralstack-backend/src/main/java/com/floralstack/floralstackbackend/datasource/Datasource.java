package com.floralstack.floralstackbackend.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Datasource {

    @Bean
    public HikariDataSource hikariDataSource() {
        HikariConfig config = new HikariConfig();
        config.setMaximumPoolSize(100);
        config.setDataSourceClassName("oracle.jdbc.pool.OracleDataSource");
        config.addDataSourceProperty("serverName", "localhost");
        config.addDataSourceProperty("port", "1521");
        config.addDataSourceProperty("databaseName", "ORCLCDB.localdomain");
        config.addDataSourceProperty("user", "admin");
        config.addDataSourceProperty("password", "admin");

        return new HikariDataSource(config);
    }
}