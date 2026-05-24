package com.livrariaJava.connection;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@Component
public class LivroConnection {
    private Connection conn;

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
        return conn = DriverManager.getConnection(URL, USER, PASSWORD);
    }
}