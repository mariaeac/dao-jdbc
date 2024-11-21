package db;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class DB {
    private static Connection conn = null;

    private static Properties loadProperties() {
        try (FileInputStream fis = new FileInputStream("src/db.properties")) {
            Properties p = new Properties();
            p.load(fis);
            return p;
        } catch (IOException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static Connection getConnection() {

        try {
            if (conn == null) {
                Properties props = loadProperties();
                String dbURL = props.getProperty("dburl");
                 conn = DriverManager.getConnection(dbURL, props);
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
        return conn;
    }

    public static void closeConnection() {
        try {
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static void closeStatement(Statement stmt) {
        try {
            if (stmt != null) {
                stmt.close();
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }

    public static void closeResultSet(ResultSet rs) {
        try {
            if (rs != null) {
                rs.close();
            }
        } catch (SQLException e) {
            throw new DBException(e.getMessage());
        }
    }


}
