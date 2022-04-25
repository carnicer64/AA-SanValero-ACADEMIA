package com.svalero.academia.servlet;

import com.svalero.academia.dao.Database;
import com.svalero.academia.dao.EnrollDao;
import com.svalero.academia.domain.Enroll;
import com.svalero.academia.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/search-enroll")
public class SearchEnrollServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        User currentUser = (User) req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect("roleDenied.jsp");
        }

        Integer idStudent = Integer.parseInt(req.getParameter("idStudent"));
        System.out.println(idStudent);
        Database database = new Database();
        EnrollDao enrollDao = new EnrollDao(database.getConnection());

        try {
            ArrayList<Enroll> enrolls = enrollDao.findEnrollByIdStudent(idStudent);
            StringBuilder result = new StringBuilder("<ol class='list-group list-group-numbered'>");

            for (Enroll enroll : enrolls) {

                result.append("<li class='list-group-item'> Matricula: ").append(enroll.getIdEnroll()).append(" Identificador curso: ").append(enroll.getEnIdSubject()).append(" Expediente: ").append(enroll.getEnNotes()).append("</li>");
            }
            result.append("</ol>");
            out.println(result);
        } catch (SQLException sqlee) {
            out.println("<div class='alert alert-danger' role='alert'>Error al conectar con la base de datos</div>");
            sqlee.printStackTrace();
        }
    }
}
