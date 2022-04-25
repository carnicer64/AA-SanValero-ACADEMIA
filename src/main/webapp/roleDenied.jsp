<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>

<%@ page import="com.svalero.academia.domain.User" %>

<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>

    <body>
        <div class="container">
            <div class="alert alert-danger" role="alert">
                <h4 class="alert-heading">Permiso denegado</h4>
                <p>Su cuenta de usuario no tiene permiso para acceder. Intentelo con otra cuenta.</p>
                <hr>
                <a href="/academia-servlet/index.jsp" class="mb-0">Volver</p>
            </div>
        </div>
    </body>
</html>