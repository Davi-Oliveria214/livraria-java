package com.livrariaJava.connection;

import com.livrariaJava.config.AppConfig;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class LivroConnection {
    private final AppConfig appConfig;

    public LivroConnection(AppConfig appConfig) {
        this.appConfig = appConfig;
    }

    public Connection connection() throws SQLException {
        return DriverManager.getConnection(appConfig.getDb_url(), appConfig.getDb_user(), appConfig.getDb_password());
    }
}