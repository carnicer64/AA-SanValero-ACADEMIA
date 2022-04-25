<%@ page language="java"
    contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"
%>
<%@ page import="com.svalero.academia.domain.User" %>
<%@ page import="com.svalero.academia.dao.Database" %>
<%@ page import="com.svalero.academia.dao.EnrollDao" %>
<%@ page import="com.svalero.academia.domain.Enroll" %>
<%@ page import="java.util.Optional" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="java.util.ArrayList" %>

<%
    User currentUser = (User) session.getAttribute("currentUser");
    if (currentUser == null) {
        response.sendRedirect("roleDenied.jsp");
    }
%>

<html>
    <head>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
    </head>

        <div class="container">
                <h1>ACADEMIA SERVLET</h1>
                <h2>Eliminación de matrículas</h2>
            <div class="accordion" id="accordionPanelsStayOpenExample">
              <% Database database = new Database();
                 EnrollDao enrollDao = new EnrollDao(database.getConnection());
                 try{
                    ArrayList<Enroll> enrolls = enrollDao.findAllEnroll();
                    for (Enroll enroll : enrolls) {
              %>
                        <div class="accordion-item">
                            <h2 class="accordion-header" id="panelsStayOpen-headingOne">
                                <button class="accordion-button" type="button" data-bs-toggle="collapse" data-bs-target="#panelsStayOpen-collapseOne" aria-expanded="true" aria-controls="panelsStayOpen-collapseOne">
                                    <%= enroll.getIdEnroll() + ". Núm estudiante: " + enroll.getEnIdStudent() +" Curso: "+ enroll.getEnIdSubject() %>
                                </button>
                            </h2>
                        </div>
                        <div id="panelsStayOpen-collapseOne" class="accordion-collapse collapse show" aria-labelledby="panelsStayOpen-headingOne">
                            <div class="accordion-body">
                                <a href="delete-enroll?idEnroll=<%= enroll.getIdEnroll() %>" class="link-danger">Eliminar</a>
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