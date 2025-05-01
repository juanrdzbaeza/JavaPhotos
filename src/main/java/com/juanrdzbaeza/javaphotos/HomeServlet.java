package com.juanrdzbaeza.javaphotos;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;

@MultipartConfig
@WebServlet(name = "homeServlet", value = "/home-servlet")
public class HomeServlet extends HttpServlet {

    private final JavaPhotosLogic javaPhotosLogic = new JavaPhotosLogic(
            new com.juanrdzbaeza.javaphotos.util.DatabaseConnection() // Inicializa la conexión a la base de datos
    );

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("username") == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        String username = (String) session.getAttribute("username");
        Part photoPart = request.getPart("photo");

        if (photoPart != null && photoPart.getSize() > 0) {
            // Guardar el archivo temporalmente
            String tempDir = System.getProperty("java.io.tmpdir");
            String tempFilePath = tempDir + "/" + photoPart.getSubmittedFileName();

            try (InputStream photoInputStream = photoPart.getInputStream()) {
                // Escribir el archivo en el sistema de archivos
                java.nio.file.Files.copy(photoInputStream, java.nio.file.Paths.get(tempFilePath), java.nio.file.StandardCopyOption.REPLACE_EXISTING);

                int userId = javaPhotosLogic.getUserIdByUsername(username);
                if (userId != -1) {
                    javaPhotosLogic.savePhoto(userId, tempFilePath, photoPart.getSubmittedFileName());
                }
                response.sendRedirect("home-servlet");
            } catch (SQLException e) {
                e.printStackTrace();
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Error saving photo.");
            } finally {
                // Eliminar el archivo temporal
                java.nio.file.Files.deleteIfExists(java.nio.file.Paths.get(tempFilePath));
            }
        } else {
            response.sendRedirect("home-servlet");
        }

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("username") != null) {
            request.getRequestDispatcher("home.jsp").forward(request, response);
        } else {
            response.sendRedirect("index.jsp");
        }
    }

}
