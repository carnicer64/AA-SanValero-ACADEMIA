<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>

<%@ page import="com.svalero.academia.dao.Database" %>
<%@ page import="com.svalero.academia.dao.SubjectDao" %>
<%@ page import="com.svalero.academia.domain.Subject" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.svalero.academia.domain.User" %>

<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>

    <body>
        <%
            int idSubject = Integer.parseInt(request.getParameter("idSubject"));

            Database database = new Database();
            SubjectDao subjectDao = new SubjectDao(database.getConnection());
            Subject subject = null;
            try {
                Optional<Subject> opSubject = subjectDao.findByIdSubject(idSubject);
                subject = opSubject.get();
        %>
                <div class="container">
                <h1> ACADEMIA SERVLET </h1>
                <h2> Datos del curso </h2>
                    <ul class="list-group">
                        <li class="list-group-item">Identificador de curso: <%= subject.getIdSubject() %></li>
                        <li class="list-group-item">Nombre del curso: <%= subject.getSubName() %></li>
                        <li class="list-group-item">Número de vacantes: <%= subject.getSubVacancies() %></li>
                        <li class="list-group-item">Profesor a cargo: <%= subject.getSubTeacher() %></li>
                        <li class="list-group-item">Precio del curso: <%= subject.getSubPrice() %></li>
                    </ul>
                </div>
        <%
            } catch (SQLException sqlee) {
        %>
            <div class='alert alert-danger' role='alert'>Error al conectar con la base de datos</div>
        <%
            sqlee.printStackTrace();
            }
        %>
    </body>
</html>