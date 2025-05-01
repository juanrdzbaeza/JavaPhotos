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


## Database File Configuration

The application uses a SQLite database file to store user and photo data. The path to the database file is dynamically assigned at runtime based on the user's home directory. By default, the database file is located at:

`<user_home>/Documents/IdeaProjects/JavaPhotos/JP.sqlite`

This ensures that the database file is stored in a consistent location relative to the user's environment. If you need to customize the database path, you can modify the `DATABASE_PATH` variable in the `DatabaseConnection` class:

```java
String userHome = System.getProperty("user.home");
DATABASE_PATH = userHome + "/Documents/IdeaProjects/JavaPhotos/JP.sqlite";
```

Alternatively, you can configure the database path using environment variables or other runtime parameters if needed.


## How to Run

1. Clone the repository.
2. Ensure you have a Java Servlet container (e.g., Apache Tomcat) installed.
3. Deploy the project to the servlet container.
4. Access the application in your browser (e.g., `http://localhost:8080/JavaPhotos`).

## License

This project is licensed under the MIT License. See the `LICENSE` file for details.