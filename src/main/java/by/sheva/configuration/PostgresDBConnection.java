package by.sheva.configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresDBConnection {

    public static final String DB_URL = "jdbc:postgresql://localhost:5432/postgres";

    public static final String DB_USER = "postgres";

    public static final String DB_PASSWORD = "postgres";

    private static PostgresDBConnection instance;

    public PostgresDBConnection() {
    }

    public static PostgresDBConnection getInstance() {
        if (instance == null) {
            instance = new PostgresDBConnection();
        }
        return instance;
    }

    public Connection getDBConnection() {

        Connection connection;
        try {
            connection = DriverManager
                    .getConnection(DB_URL,
                            DB_USER,
                            DB_PASSWORD);
            return connection;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }




}
