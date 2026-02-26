package connection;

import java.lang.reflect.InvocationTargetException;
import java.sql.Connection;
import java.sql.DriverManager;

public class LivroConnection {
    private static Connection conn;

    private static final String URL = "jdbc:mysql://localhost:3307/bd_livraria";
    private static final String USER = "";
    private static final String password = "";

    public static Connection con() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();

            conn = DriverManager.getConnection(URL, "", "");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        return conn;
    }
}