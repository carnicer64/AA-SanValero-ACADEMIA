<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ page import="com.svalero.academia.domain.User" %>
<%@ page import="com.svalero.academia.dao.Database" %>
<%@ page import="com.svalero.academia.dao.StudentDao" %>
<%@ page import="com.svalero.academia.domain.Student" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.sql.SQLException" %>
<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>

    <body>
        <%
            int idStudent = Integer.parseInt(request.getParameter("idStudent"));
            Database database = new Database();
            StudentDao studentDao = new StudentDao(database.getConnection());
            Student student = null;
            try {
                Optional<Student> optionalStudent = studentDao.findByIdStudent(idStudent);
                student = optionalStudent.get();
        %>

                <div class="container">
                <h1> ACADEMIA SERVLET </h1>
                <h2> Datos del alumno </h2>
                    <ul class="list-group">
                      <li class="list-group-item">Numero de estudiante: <%= student.getIdStudent() %></li>
                      <li class="list-group-item">Nombre: <%= student.getsName() %></li>
                      <li class="list-group-item">Apellido: <%= student.getsLastName() %></li>
                      <li class="list-group-item">Dirección: <%= student.getsAddress() %></li>
                      <li class="list-group-item">Teléfono: <%= student.getsTlp() %></li>
                      <li class="list-group-item">Email: <%= student.getsEmail() %></li>
                    </ul>
                </div>
        <%
            }catch (SQLException sqle) {
        %>
            <div class='alert alert-danger' role='alert'>Error al conectar con la base de datos</div>
        <%
            sqle.printStackTrace();
            }
        %>
    </body>
</html>