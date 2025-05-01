<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="jakarta.servlet.http.HttpSession" %>
<%
    HttpSession currentSession = request.getSession(false);
    if (currentSession == null || currentSession.getAttribute("username") == null) {
        response.sendRedirect("index.jsp");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <!-- Bootstrap CSS -->
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<nav class="navbar navbar-expand-lg navbar-light bg-light shadow">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">JavaPhotos</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNav">
            <ul class="navbar-nav ms-auto">
                <li class="nav-item">
                    <a class="nav-link active" aria-current="page" href="#">Welcome, <%= currentSession.getAttribute("username") %>!</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link text-danger" href="logout-servlet">Logout</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<div class="container-fluid mt-5">
    <form action="home-servlet" method="post" enctype="multipart/form-data" class="mt-4">
        <div class="mb-3">
            <label for="photo" class="form-label">Upload Photo:</label>
            <input type="file" id="photo" name="photo" class="form-control" required>
        </div>
        <button type="submit" class="btn btn-primary">Upload</button>
    </form>
</div>

<!-- Bootstrap JS (optional) -->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>