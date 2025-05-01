package com.juanrdzbaeza.javaphotos;

import com.juanrdzbaeza.javaphotos.util.DatabaseConnection;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JavaPhotosLogic {


    private final DatabaseConnection databaseConnection;

    public JavaPhotosLogic(DatabaseConnection databaseConnection) {
        this.databaseConnection = new DatabaseConnection();
    }


    public int getUserIdByUsername(String username) throws SQLException {
        String query = "SELECT id FROM users WHERE username = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, username);
            ResultSet resultSet = stmt.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id");
            }
        }
        return -1; // Usuario no encontrado
    }


    public void savePhoto(int userId,String imagePath, String imageName) throws SQLException {

        String query = "INSERT INTO photos (user_id, name, data) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             FileInputStream fis = new FileInputStream(imagePath)) {

            pstmt.setInt(1, userId);
            pstmt.setString(2, imageName);
            pstmt.setBinaryStream(3, fis, (int) new File(imagePath).length());
            pstmt.executeUpdate();
            System.out.println("Image saved to database.");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }


}



