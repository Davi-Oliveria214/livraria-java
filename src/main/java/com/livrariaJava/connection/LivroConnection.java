package com.livrariaJava.connection;

import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class LivroConnection {

    private final String URL;
    private final String USER;
    private final String PASSWORD;

    public LivroConnection() {

        this.URL = System.getenv("DB_URL");
        this.USER = System.getenv("DB_USER");
        this.PASSWORD = System.getenv("DB_PASSWORD");
    }

    public Connection connection() throws SQLException {
        Connection conn;
        return conn = DriverManager.getConnection(URL,USER,PASSWORD);
    }
}