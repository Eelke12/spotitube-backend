package dea.eelkedejong.spotitube.dbconnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PostgresConnection {

    public static Connection getConnection() {

        Connection conn = null;

        String dbURL = "jdbc:postgresql://localhost/spotitube";
        String user = "spotitube";
        String pass = "spotitube";

        try {
            Class.forName("org.postgresql.Driver");
            conn =  DriverManager.getConnection(dbURL, user, pass);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        return conn;
    }
}
