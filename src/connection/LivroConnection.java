package connection;

import io.github.cdimascio.dotenv.Dotenv;

import java.sql.Connection;
import java.sql.DriverManager;

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

    public Connection connection() {
        try {
            if (conn == null || conn.isClosed()) {
                conn = DriverManager.getConnection(URL, USER, PASSWORD);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}