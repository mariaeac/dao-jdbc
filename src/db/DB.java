package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DB {
    private static Connection conn = null;

    private static Properties loadProperties() {
        try (FileInputStream fis = new FileInputStream("db.properties")) {
            Properties p = new Properties();
            p.load(fis);
            return p;
        } catch (IOException e) {
            throw new DBException(e.getMessage());
        }
    }

    private static Connection getConnection() {

        try {
            if (conn == null) {
                Properties props = loadProperties();
                String dbURL = props.getProperty("dbURL");
                 conn = DriverManager.getConnection(dbURL, props);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        return conn;
    }

}
