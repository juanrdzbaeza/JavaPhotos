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

        if (isValidUser) {
            HttpSession session = request.getSession();
            session.setAttribute("username", username); // Guarda el usuario en la sesión
            response.sendRedirect("home.jsp");
        } else {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Login Failed</title>");
            out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
            out.println("</head>");
            out.println("<body class=\"bg-light d-flex justify-content-center align-items-center vh-100\">");
            out.println("<div class=\"card p-4 shadow\" style=\"width: 22rem;\">");
            out.println("<h1 class=\"text-center text-danger\">Login Failed</h1>");
            out.println("<p class=\"text-center\">Invalid username or password.</p>");
            out.println("<div class=\"text-center mt-3\">");
            out.println("<a href=\"index.jsp\" class=\"btn btn-primary w-100\">Back to Login</a>");
            out.println("</div>");
            out.println("</div>");
            out.println("</body>");
            out.println("</html>");
        }
        out.close();
    }

}