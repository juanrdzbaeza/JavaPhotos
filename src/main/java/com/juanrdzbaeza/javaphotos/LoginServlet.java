package com.juanrdzbaeza.javaphotos;

import java.io.*;

import com.juanrdzbaeza.javaphotos.dao.UserDAO;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "loginServlet", value = "/login-servlet")
public class LoginServlet extends HttpServlet {


    private final JavaPhotosLogic javaPhotosLogic = new JavaPhotosLogic(
            new com.juanrdzbaeza.javaphotos.util.DatabaseConnection() // Inicializa la conexión a la base de datos
    );

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {

        response.setContentType("text/html");

        // Obtener los datos del formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Validar los datos con UserDAO
        UserDAO userDAO = new UserDAO();
        boolean isValidUser = userDAO.validateUser(username, password);

        // Generar la respuesta
        PrintWriter out = response.getWriter();
        out.println("<html><body>");
        if (isValidUser) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username); // Guarda el usuario en la sesión
            response.sendRedirect("home.jsp");
        } else {
            out.println("<h1>Login failed! Invalid username or password.</h1>");
            out.println("<a href=\"index.jsp\">Back to the login</a>");
        }
        out.println("</body></html>");


    }

}