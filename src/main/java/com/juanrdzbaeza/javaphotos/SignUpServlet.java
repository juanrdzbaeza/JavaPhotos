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
        out.println("<html><body>");
        if (isUserCreated) {
            out.println("<h1>Sign Up successful! You can now log in.</h1>");
            out.println("<a href=\"index.jsp\">Go to Login</a>");
        } else {
            out.println("<h1>Sign Up failed! Username might already exist.</h1>");
            out.println("<a href=\"index.jsp\">Try Again</a>");
        }
        out.println("</body></html>");
    }
}
