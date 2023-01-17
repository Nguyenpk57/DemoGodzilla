package com.ntt.godzilla.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;

public class BaseDatabaseConfig extends HikariConfig {
    public DataSource readDatabase(String url, String username, String password) {
        HikariConfig hikariConfig = new HikariConfig();
        try {
            hikariConfig.setJdbcUrl(url);
            hikariConfig.setDriverClassName(this.getDriverClassName());
            hikariConfig.setUsername(username);
            hikariConfig.setPassword(password);
            hikariConfig.setConnectionTimeout(this.getConnectionTimeout());
            hikariConfig.setIdleTimeout(this.getIdleTimeout());
            hikariConfig.setMaxLifetime(this.getMaxLifetime());
            hikariConfig.setMinimumIdle(this.getMinimumIdle());
            hikariConfig.setMaximumPoolSize(this.getMaximumPoolSize());
        } catch (Exception e) {
            hikariConfig = this;
        }
        return new HikariDataSource(hikariConfig);

    }
}
