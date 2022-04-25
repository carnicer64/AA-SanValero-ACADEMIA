package com.svalero.academia.servlet;


import com.svalero.academia.dao.Database;
import com.svalero.academia.dao.UserDao;
import com.svalero.academia.domain.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;

@WebServlet("/login")
public class UserLoginServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String password = req.getParameter("password");
        String username = req.getParameter("username");

        Database database = new Database();
        UserDao userDao = new UserDao(database.getConnection());

        try{
            Optional<User> user = userDao.loginUser(username, password);
            if (user.isPresent()) {
                HttpSession session = req.getSession(true);
                session.setAttribute("currentUser", user.get());
                resp.sendRedirect("index.jsp");
            }else {
                resp.sendRedirect("userLoginError.jsp");
            }

        }catch (SQLException sqle) {
            sqle.printStackTrace();
        }

    }
}
