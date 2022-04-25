<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ page import="com.svalero.academia.domain.User" %>
<%@ page import="com.svalero.academia.dao.Database" %>
<%@ page import="com.svalero.academia.dao.EnrollDao" %>
<%@ page import="com.svalero.academia.dao.SubjectDao" %>
<%@ page import="com.svalero.academia.dao.StudentDao" %>
<%@ page import="com.svalero.academia.domain.Subject" %>
<%@ page import="com.svalero.academia.domain.Enroll" %>
<%@ page import="com.svalero.academia.domain.Student" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>

<%
    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("roleDenied.jsp");
    }

    Database database = new Database();
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
                        $.post("enroll-student", formValue, function(data) {
                            $("#result").html(data);
                        });
                    });
                });
        </script>
        <div class="container">
                <h1>ACADEMIA SERVLET</h1>
                <h2>Matriculación de alumnos</h2>
                <form>
                    <div class="mb-2">
                        <label for="idStudent" class="form-label">Elige el número del alumno a matricular</label>
                        <select class="form-control" id="idStudent" name="idStudent">
                        <%
                            StudentDao studentDao = new StudentDao(database.getConnection());
                            ArrayList<Student> students = studentDao.findAllStudents();
                            for (Student student : students) {
                                out.println("<option value=\"" + student.getIdStudent() + "\">" + student.getsName() + " " + student.getsLastName() + "</option>");
                            }
                        %>
                        </select>
                    </div>
                    <div class="mb-2">
                        <label for="idSubject" class="form-label">Elige el curso de matriculación</label>
                        <select class="form-control" id="idSubject" name="idSubject">
                        <%
                            SubjectDao subjectDao = new SubjectDao(database.getConnection());
                            ArrayList<Subject> subjects = subjectDao.findAllSubjects();
                            for (Subject subject : subjects) {
                                out.println("<option value=\"" + subject.getIdSubject() + "\">" + subject.getSubName() + "</option>");
                            }
                        %>
                        </select>
                    </div>
                    <div class="mb-2">
                        <label for="idEnroll" class="form-label">Número de identificación de la matrícula</label>
                        <input name="idEnroll" type="number" class="form-control w-25" id="idEnroll" name="idEnroll">
                    </div>
                    <div class="mb-2">
                        <label for="enPrice" class="form-label">Precio de la matrícula</label>
                        <input name="enPrice" type="number" class="form-control w-25" id="enPrice" name="enPrice">
                    </div>
                    <div class="mb-2">
                        <label for="enQuotes" class="form-label">Número de cuotas de pago</label>
                        <input name="enQuotes" type="number" class="form-control w-25" id="enQuotes" name="enQuotes">
                    </div>
                    <div class="mb-2">
                        <label for="enNotes" class="form-label">Expediente del alumno (aprobado/suspendido/pendiente)</label>
                        <input name="enNotes" type="text" class="form-control w-25" id="enNotes" name="enNotes">
                    </div>
                    <button type="submit" class="btn btn-primary">Enviar</button>
                </form>
                <div id="result"></div>
        </div>
    </body>
</html>