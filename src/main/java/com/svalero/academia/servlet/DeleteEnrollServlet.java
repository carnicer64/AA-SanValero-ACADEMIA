package com.svalero.academia.servlet;

import com.svalero.academia.dao.Database;
import com.svalero.academia.dao.EnrollDao;
import com.svalero.academia.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/delete-enroll")
public class DeleteEnrollServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        out.println("<head><link href='https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css' rel='stylesheet' integrity='sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3' crossorigin='anonymous'></head>");

        User currentUser = (User) req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect("roleDenied.jsp");
        }

        System.out.println("Prueba");

        Integer idEnroll = Integer.parseInt(req.getParameter("idEnroll"));

        System.out.println(idEnroll);

        Database database = new Database();
        EnrollDao enrollDao = new EnrollDao(database.getConnection());

        try {
            enrollDao.deleteEnroll(idEnroll);
            out.println("<div class='alert alert-success' role='alert'>\n" +
                    "  <h4 class='alert-heading'>ELIMINACIÓN CORRECTA</h4>\n" +
                    "  <p>La matricula se ha eliminado de forma correcta.</p>\n" +
                    "  <hr>\n" +
                    "  <p class='mb-0'><a href='/academia-servlet/index.jsp'>Volver al menú</a></p>\n" +
                    "</div>");
        } catch (SQLException sqlee) {
            out.println("<div class='alert alert-danger' role='alert'>Se ha producido un error al conectar con la base de datos</div>");
            sqlee.printStackTrace();
        }

    }
}
