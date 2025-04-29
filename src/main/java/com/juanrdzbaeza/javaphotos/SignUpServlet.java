package com.juanrdzbaeza.javaphotos;

import com.juanrdzbaeza.javaphotos.dao.UserDAO;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "signUpServlet", value = "/signup-servlet")
public class SignUpServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        response.setContentType("text/html");

        // Obtener los datos del formulario
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        // Guardar el usuario en la base de datos
        UserDAO userDAO = new UserDAO();
        boolean isUserCreated = userDAO.createUser(username, password);

        // Generar la respuesta
        PrintWriter out = response.getWriter();
        out.println("<!DOCTYPE html>");
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Sign Up</title>");
        out.println("<link href=\"https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css\" rel=\"stylesheet\">");
        out.println("</head>");
        out.println("<body class=\"bg-light d-flex justify-content-center align-items-center vh-100\">");
        out.println("<div class=\"card p-4 shadow\" style=\"width: 22rem;\">");

        if (isUserCreated) {
            out.println("<h1 class=\"text-center text-success\">Sign Up Successful!</h1>");
            out.println("<p class=\"text-center\">You can now log in.</p>");
            out.println("<div class=\"text-center mt-3\">");
            out.println("<a href=\"index.jsp\" class=\"btn btn-primary w-100\">Go to Login</a>");
            out.println("</div>");
        } else {
            out.println("<h1 class=\"text-center text-danger\">Sign Up Failed!</h1>");
            out.println("<p class=\"text-center\">Username might already exist.</p>");
            out.println("<div class=\"text-center mt-3\">");
            out.println("<a href=\"signup.jsp\" class=\"btn btn-warning w-100\">Try Again</a>");
            out.println("</div>");
        }

        out.println("</div>");
        out.println("</body>");
        out.println("</html>");
        out.close();
    }
}
