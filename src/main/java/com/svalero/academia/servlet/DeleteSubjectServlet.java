package com.svalero.academia.servlet;

import com.svalero.academia.dao.Database;
import com.svalero.academia.dao.SubjectDao;
import com.svalero.academia.domain.User;
import com.svalero.academia.exception.SubjectDoesNotExistException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/delete-subject")
public class DeleteSubjectServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<head><link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3' crossorigin='anonymous'></head>");

        User currentUser = (User) req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect("roleDenied.jsp");
        }

        Integer idSubject = Integer.parseInt(req.getParameter("idSubject"));

        Database database = new Database();
        SubjectDao subjectDao = new SubjectDao(database.getConnection());
        try{
            subjectDao.deleteSubjectByID(idSubject);
            out.println("<div class='alert alert-success' role='alert'>\n" +
                    "  <h4 class='alert-heading'>ELIMINACIÓN CORRECTA</h4>\n" +
                    "  <p>El curso se ha eliminado de forma correcta.</p>\n" +
                    "  <hr>\n" +
                    "  <p class='mb-0'><a href='/academia-servlet/index.jsp'>Volver al menú</a></p>\n" +
                    "</div>");
        } catch (SQLException sqlee) {
            out.println("<div class='alert alert-danger' role='alert'>Se ha producido un error al conectar con la base de datos</div>");
            sqlee.printStackTrace();
        } catch (SubjectDoesNotExistException sdnee) {
            out.println("<div class='alert alert-danger' role='alert'>El registro que intenta borrar no existe</div>");
            sdnee.printStackTrace();
        }
    }
}
