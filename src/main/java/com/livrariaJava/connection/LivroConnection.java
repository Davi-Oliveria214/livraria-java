package com.livrariaJava.connection;

import io.github.cdimascio.dotenv.Dotenv;
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
        Dotenv dotEnv = Dotenv.load();

        this.URL = dotEnv.get("DB_URL");
        this.USER = dotEnv.get("DB_USER");
        this.PASSWORD = dotEnv.get("DB_PASSWORD");
    }

    public Connection connection() throws SQLException {
        Connection conn;
        return conn = DriverManager.getConnection(URL,USER,PASSWORD);
    }
}