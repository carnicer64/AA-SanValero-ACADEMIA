package com.svalero.academia.servlet;

import com.svalero.academia.dao.Database;
import com.svalero.academia.dao.UserDao;
import com.svalero.academia.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/modify-user")
public class ModifyUserServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        User currentUser = (User) req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect("roleDenied.jsp");
        }

        Integer idUser = Integer.parseInt(req.getParameter("idUser"));
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String role = req.getParameter("role");
        String userFirstName = req.getParameter("userFirstName");
        String userLastName = req.getParameter("userLastName");
        String userTlp = req.getParameter("userTlp");
        String userEmail = req.getParameter("userEmail");

        System.out.println(password);

        User user = new User(username, password, idUser, role, userFirstName, userLastName, userTlp, userEmail);

        Database database = new Database();
        UserDao userdao = new UserDao(database.getConnection());

        try {
            userdao.modifyUser(idUser, user);
            out.println("<div class='alert alert-success' role='alert'>\n" +
                    "  <h4 class='alert-heading'>Modificación correcta</h4>\n" +
                    "  <p>El usuario se ha modificado de forma correcta.</p>\n" +
                    "  <hr>\n" +
                    "  <p class='mb-0'><a href='/academia-servlet/index.jsp'>Volver al menú</a></p>\n" +
                    "</div>");

        }catch (SQLException sqlee) {
            out.println("<div class='alert alert-danger' role='alert'>Se ha producido un error al conectar con la base de datos</div>");
            sqlee.printStackTrace();
        }
    }
}
