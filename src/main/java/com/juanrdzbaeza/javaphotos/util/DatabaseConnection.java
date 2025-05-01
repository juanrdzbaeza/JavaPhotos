package com.juanrdzbaeza.javaphotos.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DatabaseConnection {

    // Path to the database file
    public static String DATABASE_PATH;

    static {
        String userHome = System.getProperty("user.home");
        DATABASE_PATH = userHome + "/Documents/IdeaProjects/JavaPhotos/JP.sqlite"; // Path to the database file must be assigned here
    }


    private static final String URL = "jdbc:sqlite:" + DATABASE_PATH;

    public DatabaseConnection() {
        // Constructor
        initializeDatabase();
    }
    static {
        try {
            Class.forName("org.sqlite.JDBC");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private void initializeDatabase() {
        String createTableUsersSQL = "CREATE TABLE IF NOT EXISTS users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "username TEXT NOT NULL UNIQUE , "
                + "password TEXT NOT NULL "
                + ");";

        String createTablePhotosSQL = "CREATE TABLE IF NOT EXISTS photos ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "user_id INTEGER NOT NULL, "
                + "name TEXT NOT NULL UNIQUE , "
                + "data BLOB NOT NULL, "
                + "FOREIGN KEY(user_id) REFERENCES users(id) "
                + ");";

        try (Connection conn = DriverManager.getConnection(this.URL);
             Statement stmt = conn.createStatement()) {

            stmt.execute(createTableUsersSQL);
            stmt.execute(createTablePhotosSQL);
            System.out.println("Database initialized successfully.");

        } catch (SQLException e) {
            Logger.getLogger(DatabaseConnection.class.getName()).log(Level.SEVERE, null, e);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection( URL );
    }
}
