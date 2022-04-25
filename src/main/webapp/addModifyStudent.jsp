<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>

<%@ page import="com.svalero.academia.dao.Database" %>
<%@ page import="com.svalero.academia.dao.StudentDao" %>
<%@ page import="com.svalero.academia.domain.Student" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="com.svalero.academia.domain.User" %>


<%

    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("roleDenied.jsp");
    }

    String text = "";
    String idStudent = request.getParameter("idStudent");
    Student student = null;

    if (idStudent != null) {
    text = "Modificar";
    Database database = new Database();
    StudentDao studentDao = new StudentDao(database.getConnection());

        try{
            Optional<Student> opStudent = studentDao.findByIdStudent(Integer.parseInt(idStudent));
            student = opStudent.get(); //.get() es un metodo de Optional
        }catch (SQLException sqlee){
            sqlee.printStackTrace();
        }
    } else{
        text = "Registrar";
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
                        $.post("add-modify-student", formValue, function(data) {
                            $("#result").html(data);
                        });
                    });
                });
        </script>

        <div class="container">
                <h1> ACADEMIA SERVLET </h1>
                <h2><%= text %> alumno</h2>
                <form>
                    <div class="mb-2">
                        <label for="idStudent" class="form-label">Número de ID</label>
                        <input name="idStudent" type="number" class="form-control w-25" id="idStudent" value="<% if (student != null) out.print(student.getIdStudent()); %>">
                    </div>
                    <div class="mb-2">
                        <label for="sName" class="form-label">Nombre del alumno</label>
                        <input name="sName" type="text" class="form-control w-25" id="sName" value="<% if (student != null) out.print(student.getsName()); %>">
                    </div>
                    <div class="mb-2">
                        <label for="sLastName" class="form-label">Apellido del alumno</label>
                        <input name="sLastName" type="text" class="form-control w-25" id="sLastName" value="<% if (student != null) out.print(student.getsLastName()); %>">
                    </div>
                    <div class="mb-2">
                        <label for="sAddress" class="form-label">Dirección del alumno</label>
                        <input name="sAddress" type="text" class="form-control w-25" id="sAddress" value="<% if (student != null) out.print(student.getsAddress()); %>">
                    </div>
                    <div class="mb-2">
                        <label for="sTlp" class="form-label">Teléfono del alumno</label>
                        <input name="sTlp" type="text" class="form-control w-25" id="sTlp" value="<% if (student != null) out.print(student.getsTlp()); %>">
                    </div>
                    <div class="mb-2">
                        <label for="sEmail" class="form-label">Email del alumno</label>
                        <input name="sEmail" type="email" class="form-control w-25" id="sEmail" value="<% if (student != null) out.print(student.getsEmail()); %>">
                    </div>
                    <input type="hidden" name="action" value="<% if (student != null) out.print("modify"); else out.print("register"); %>">
                    <button type="submit" class="btn btn-primary"><%= text %></button>
                </form>
                <div id="result"></div>
        </div>
    </body>
</html>