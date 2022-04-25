<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ page import="com.svalero.academia.domain.User" %>

<%
    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("login.jsp");
    }
%>

<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>
<body>
    <div class="container">
        <h1> ACADEMIA SERVLET</h1>
        <h2> Menú de opciones </h2>
        <div class="list-group">
          <a href="/academia-servlet/studentList.jsp" class="list-group-item list-group-item-action">1. Lista de alumnos</a>
          <a href="/academia-servlet/searchStudent.jsp" class="list-group-item list-group-item-action">2. Busqueda de alumnos</a>
          <a href="/academia-servlet/subjectList.jsp" class="list-group-item list-group-item-action">3. Lista de cursos</a>
          <a href="/academia-servlet/searchSubjects.jsp" class="list-group-item list-group-item-action">4. Busqueda de cursos</a>
          <a href="/academia-servlet/searchEnroll.jsp" class="list-group-item list-group-item-action">5. Ver matriculas</a>
          <a href="/academia-servlet/modifyUser.jsp" class="list-group-item list-group-item-action">6. Zona de usuario</a>

        <%
          if ((currentUser != null) && (currentUser.getRole().equals("Admin"))) {
        %>
          <a href="/academia-servlet/addModifyStudent.jsp" class="list-group-item list-group-item-action">7. Registrar nuevo alumno</a>
          <a href="/academia-servlet/addModifySubject.jsp" class="list-group-item list-group-item-action">8. Registrar nuevo curso</a>
          <a href="/academia-servlet/enrollStudent.jsp" class="list-group-item list-group-item-action">9. Registrar nueva matrícula</a>
          <a href="/academia-servlet/deleteEnroll.jsp" class="list-group-item list-group-item-action">10. Eliminar matrículas</a>
        <%
          }
        %>

        </div>
    </div>
</body>
</html>
