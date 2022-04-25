<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ page import="com.svalero.academia.dao.Database" %>
<%@ page import="com.svalero.academia.domain.User" %>
<%@ page import="com.svalero.academia.dao.UserDao" %>
<%@ page import="java.sql.SQLException" %>

<%

    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("roleDenied.jsp");
    }
%>

<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    </head>

    <body>
        <script type="text/javascript">
                $(document).ready(function() {
                    $("form").on("submit", function(event) {
                        event.preventDefault();
                        var formValue = $(this).serialize();
                        $.post("modify-user", formValue, function(data) {
                            $("#result").html(data);
                        });
                    });
                });
        </script>

        <div class="container">
                <h1> ACADEMIA SERVLET </h1>
                <h2>Zona de usuario</h2>
                <form>
                    <div class="mb-2">
                        <label for="username" class="form-label">Nick de usuario</label>
                        <input name="username" type="text" class="form-control w-25" id="username" value="<% if (currentUser != null) out.print(currentUser.getUsername()); %>">
                    </div>
                    <div class="mb-2">
                        <label for="password" class="form-label">Nueva Contraseña</label>
                        <input name="password" type="password" class="form-control w-25" id="password" value="<% if (currentUser != null) out.print(currentUser.getPassword()); %>">
                    </div>
                    <div class="mb-2">
                        <label for="userFirstName" class="form-label">Nombre del usuario</label>
                        <input name="userFirstName" type="text" class="form-control w-25" id="userFirstName" value="<% if (currentUser != null) out.print(currentUser.getUserFirstName()); %>">
                    </div>
                    <div class="mb-2">
                        <label for="userLastName" class="form-label">Apellido del usuario</label>
                        <input name="userLastName" type="text" class="form-control w-25" id="userLastName" value="<% if (currentUser != null) out.print(currentUser.getUserLastName()); %>">
                    </div>
                    <div class="mb-2">
                        <label for="userTlp" class="form-label">Teléfono del alumno</label>
                        <input name="userTlp" type="text" class="form-control w-25" id="userTlp" value="<% if (currentUser != null) out.print(currentUser.getUserTlp()); %>">
                    </div>
                    <div class="mb-2">
                        <label for="userEmail" class="form-label">Email del alumno</label>
                        <input name="userEmail" type="email" class="form-control w-25" id="userEmail" value="<% if (currentUser != null) out.print(currentUser.getUserEmail()); %>">
                    </div>
                    <input type="hidden" name="idUser" value="<% if (currentUser != null) out.print(currentUser.getIdUser()); %>">
                    <input type="hidden" name="role" value="<% if (currentUser != null) out.print(currentUser.getRole()); %>">
                    <button type="submit" class="btn btn-primary">Enviar</button>
                </form>
                <div id="result"></div>
        </div>
    </body>
</html>