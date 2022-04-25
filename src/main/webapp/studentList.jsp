<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>

<%@ page import="com.svalero.academia.dao.Database" %>
<%@ page import="com.svalero.academia.dao.StudentDao" %>
<%@ page import="com.svalero.academia.domain.Student" %>
<%@ page import="java.util.List" %>
<%@ page import="java.sql.SQLException" %>
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
            <h1> ACADEMIA SERVLET </h1>
            <h2> Listado de alumnos </h2>
            <div class="accordion" id="accordionPanelsStayOpenExample">
              <% Database database = new Database();
                 StudentDao studentDao = new StudentDao(database.getConnection());
                 try{
                    List<Student> students = studentDao.findAllStudents();
                    for (Student student : students) {
              %>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="panelsStayOpen-headingOne">
                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true" aria-controls="panelsStayOpen-collapseOne">
                                    <%= student.getIdStudent() + ". " + student.getsName() +" "+ student.getsLastName() %>
                                </button>
                            </h2>
                        </div>
                        <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show" aria-labelledby="panelsStayOpen-headingOne">
                            <div class="accordion-body">
                                <a href="detailStudent.jsp?idStudent=<%= student.getIdStudent() %>" class="link-success">Datos del alumno</a>
                                <a href="addModifyStudent.jsp?idStudent=<%= student.getIdStudent() %>" class="link-primary">Modificar</a>
                                <a href="delete-student?idStudent=<%= student.getIdStudent() %>" class="link-danger">Eliminar</a>
                            </div>
                        </div>
              <%
                   }
                } catch (SQLException sqle) {
              %>
                    <div class="alert alert-danger" role="alert">
                        Se ha producido un error al conectar la base de datos
                    </div>
              <%
                }
              %>
            </div>
        </div>
    </body>
</html>