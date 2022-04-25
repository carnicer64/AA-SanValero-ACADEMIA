package com.svalero.academia.servlet;

import com.svalero.academia.dao.Database;
import com.svalero.academia.dao.SubjectDao;
import com.svalero.academia.domain.Subject;
import com.svalero.academia.domain.User;
import com.svalero.academia.exception.SubjectAlreadyExistsException;
import com.svalero.academia.exception.SubjectDoesNotExistException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/add-modify-subject")
public class AddModifySubjectServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        User currentUser = (User) req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect("accesonopermitido.jsp");
        }

        Integer idSubject = Integer.parseInt(req.getParameter("idSubject"));
        String subName = req.getParameter("subName");
        Integer subVacancies = Integer.parseInt(req.getParameter("subVacancies"));
        String subTeacher = req.getParameter("subTeacher");
        Integer subPrice = Integer.parseInt(req.getParameter("subPrice"));
        String action = req.getParameter("action");

        Subject subject = new Subject(idSubject, subName, subVacancies, subTeacher, subPrice);

        Database database = new Database();
        SubjectDao subjectDao = new SubjectDao(database.getConnection());

        try {
            if (action.equals("register")) {
                subjectDao.addSubject(subject);
                out.println("<div class='alert alert-success' role='alert'>\n" +
                        "  <h4 class='alert-heading'>Registro correcto</h4>\n" +
                        "  <p>El curso se ha registrado de forma correcta.</p>\n" +
                        "  <hr>\n" +
                        "  <p class='mb-0'><a href='/academia-servlet/index.jsp'>Volver al menú</a></p>\n" +
                        "</div>");
            } else {
                subjectDao.modifySubjectById(idSubject, subject);
                out.println("<div class='alert alert-success' role='alert'>\n" +
                        "  <h4 class='alert-heading'>Modificación correcta</h4>\n" +
                        "  <p>El curso se ha modificado de forma correcta.</p>\n" +
                        "  <hr>\n" +
                        "  <p class='mb-0'><a href='/academia-servlet/index.jsp'>Volver al menú</a></p>\n" +
                        "</div>");
            }
        } catch (SQLException sqlee) {
            out.println("<div class='alert alert-danger' role='alert'>Se ha producido un error al conectar con la base de datos</div>");
            sqlee.printStackTrace();
        }catch (SubjectAlreadyExistsException saee) {
            out.println("<div class='alert alert-danger' role='alert'>Ya existe este curso</div>");
            saee.printStackTrace();
        }catch (SubjectDoesNotExistException sdnee) {
            out.println("<div class='alert alert-danger' role='alert'>El estudiante que intentas modificar no existe</div>");
            sdnee.printStackTrace();
        }


    }
}
