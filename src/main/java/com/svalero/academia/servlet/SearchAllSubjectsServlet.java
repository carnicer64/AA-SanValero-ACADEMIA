package com.svalero.academia.servlet;

import com.svalero.academia.dao.Database;
import com.svalero.academia.dao.SubjectDao;
import com.svalero.academia.domain.Student;
import com.svalero.academia.domain.Subject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/search-subjects")
public class SearchAllSubjectsServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        String searchText = req.getParameter("searchText");

        Database database = new Database();
        SubjectDao subjectDao = new SubjectDao(database.getConnection());
        try{
            ArrayList<Subject> subjects = subjectDao.findAllSubjects(searchText);
            StringBuilder result = new StringBuilder("<ul class='list-group'>");

            for(Subject subject : subjects) {
                result.append("<li class='list-group-item'> Identificador del curso: ").append(subject.getIdSubject()).append("</li>");
                result.append("<li class='list-group-item'> Nombre del curso: ").append(subject.getSubName()).append("</li>");
                result.append("<li class='list-group-item'> Número de vacantes: ").append(subject.getSubVacancies()).append("</li>");
                result.append("<li class='list-group-item'> Profesor a cargo: ").append(subject.getSubTeacher()).append("</li>");
                result.append("<li class='list-group-item'> Precio del curso: ").append(subject.getSubPrice()).append("</li>");
            }
            result.append("</ul>");
            out.println(result);

        } catch (SQLException sqlee) {
            out.println("<div class='alert alert-danger' role='alert'>Error al conectar con la base de datos</div>");
            sqlee.printStackTrace();
        }
    }
}
