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

<%
    User currentUser = (User) session.getAttribute("currentUser");
        if (currentUser == null) {
            response.sendRedirect("roleDenied.jsp");
        }

    String text = "";
    String idSubject = request.getParameter("idSubject");
    Subject subject = null;

    if (idSubject != null) {
    text = "Modificar";
    Database database = new Database();
    SubjectDao subjectDao = new SubjectDao(database.getConnection());

        try {
            Optional<Subject> opSubject = subjectDao.findByIdSubject(Integer.parseInt(idSubject));
            subject = opSubject.get();
        } catch (SQLException sqlee){
            sqlee.printStackTrace();
        }
    } else {
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
                    $.post("add-modify-subject", formValue, function(data) {
                        $("#result").html(data);
                    });
                });
            });
        </script>

        <div class="container">
            <h1>ACADEMIA SERVLET</h1>
            <h2><%= text %> curso</h2>
            <form>
                <div class="mb-2">
                    <label for="idSubject" class="form-label">Número de ID</label>
                    <input name="idSubject" type="number" class="form-control w-25" id="idSubject" value="<% if (subject != null) out.print(subject.getIdSubject()); %>">
                </div>
                <div class="mb-2">
                    <label for="subName" class="form-label">Nombre del curso</label>
                    <input name="subName" type="text" class="form-control w-25" id="subName" value="<% if (subject != null) out.print(subject.getSubName()); %>">
                </div>
                <div class="mb-2">
                    <label for="subVacancies" class="form-label">Numero de vacantes</label>
                    <input name="subVacancies" type="number" class="form-control w-25" id="subVacancies" value="<% if (subject != null) out.print(subject.getSubVacancies()); %>">
                </div>
                <div class="mb-2">
                    <label for="subTeacher" class="form-label">Profesor del curso</label>
                    <input name="subTeacher" type="text" class="form-control w-25" id="subTeacher" value="<% if (subject != null) out.print(subject.getSubTeacher()); %>">
                </div>
                <div class="mb-2">
                    <label for="subPrice" class="form-label">Precio del curso</label>
                    <input name="subPrice" type="number" class="form-control w-25" id="subPrice" value="<% if (subject != null) out.print(subject.getSubPrice()); %>">
                </div>
                <input type="hidden" name="action" value="<% if (subject != null) out.print("modify"); else out.print("register"); %>">
                <button type="submit" class="btn btn-primary"><%= text %></button>
            </form>
            <div id="result"></div>
        </div>
    </body>
</html>