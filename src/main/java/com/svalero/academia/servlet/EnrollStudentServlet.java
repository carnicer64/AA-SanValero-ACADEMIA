package com.svalero.academia.servlet;

import com.svalero.academia.dao.Database;
import com.svalero.academia.dao.EnrollDao;
import com.svalero.academia.domain.Enroll;
import com.svalero.academia.domain.User;
import com.svalero.academia.exception.EnrollAlreadyExistsException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/enroll-student")
public class EnrollStudentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        User currentUser = (User) req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect("roleDenied.jsp");
        }


        Integer idEnroll = Integer.parseInt(req.getParameter("idEnroll"));
        Integer enPrice = Integer.parseInt(req.getParameter("enPrice"));
        Integer enQuotes = Integer.parseInt(req.getParameter("enQuotes"));
        String enNotes = req.getParameter("enNotes");
        Integer idSubject = Integer.parseInt(req.getParameter("idSubject"));
        Integer idStudent = Integer.parseInt(req.getParameter("idStudent"));
        Enroll enroll = new Enroll(idEnroll,enPrice,enQuotes,enNotes);

        System.out.println(idEnroll);
        System.out.println(enPrice);
        System.out.println(enQuotes);
        System.out.println(enNotes);
        System.out.println(idSubject);
        System.out.println(idStudent);

        Database database = new Database();
        EnrollDao enrollDao = new EnrollDao(database.getConnection());

        try{
            enrollDao.addEnroll(enroll, idStudent, idSubject);
            out.println("<div class='alert alert-success' role='alert'>\n" +
                    "  <h4 class='alert-heading'>Matricula registrada</h4>\n" +
                    "  <p>El alumno se ha matriculado de forma correcta.</p>\n" +
                    "  <hr>\n" +
                    "  <p class='mb-0'><a href='/academia-servlet/index.jsp'>Volver al menú</a></p>\n" +
                    "</div>");
        }catch (SQLException sqlee) {
            out.println("<div class='alert alert-danger' role='alert'>Se ha producido un error al conectar con la base de datos</div>");
            sqlee.printStackTrace();
        }catch (EnrollAlreadyExistsException eaee) {
            out.println("<div class='alert alert-danger' role='alert'>Esta matricula ya existe</div>");
            eaee.printStackTrace();
        }


    }
}
