# JavaPhotos

JavaPhotos is a simple example of a login and signup system implemented using JSP, Servlets, and a SQLite database. It demonstrates the following features:

- User authentication (login and logout).
- User registration (signup).
- Session management using `HttpSession`.
- Integration with a SQLite database for storing user credentials.
- Use of Bootstrap for responsive and modern UI design.

## Features

1. **Login**: Users can log in with their credentials. If the login fails, an error message is displayed.
2. **Signup**: New users can register by providing a unique username and password.
3. **Session Management**: Logged-in users are redirected to a home page, and their session is maintained.
4. **Logout**: Users can log out, which invalidates their session.
5. **Database**: User data is stored in a SQLite database.

## Technologies Used

- **Java**: Core language for backend logic.
- **JSP & Servlets**: For dynamic web pages and request handling.
- **SQLite**: Lightweight database for storing user data.
- **Bootstrap**: For styling and responsive design.

## How to Run

1. Clone the repository.
2. Ensure you have a Java Servlet container (e.g., Apache Tomcat) installed.
3. Deploy the project to the servlet container.
4. Access the application in your browser (e.g., `http://localhost:8080/JavaPhotos`).

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.