package com.svalero.academia.servlet;

import com.svalero.academia.dao.Database;
import com.svalero.academia.dao.StudentDao;
import com.svalero.academia.domain.Student;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/search-students")
public class SearchAllStudentsServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();
        //Texto a buscar -> searchText
        String searchText = req.getParameter("searchText");

        Database database = new Database();
        StudentDao studentDao = new StudentDao(database.getConnection());

        try{
            ArrayList<Student> students = studentDao.findAllStudents(searchText);
            StringBuilder result = new StringBuilder("<ul class='list-group'>");

            for(Student student : students) {
                result.append("<li class='list-group-item'> Número de estudiante: ").append(student.getIdStudent()).append("</li>");
                result.append("<li class='list-group-item'> Nombre: ").append(student.getsName()).append("</li>");
                result.append("<li class='list-group-item'> Apellido: ").append(student.getsLastName()).append("</li>");
                result.append("<li class='list-group-item'> Dirección: ").append(student.getsAddress()).append("</li>");
                result.append("<li class='list-group-item'> Teléfono: ").append(student.getsTlp()).append("</li>");
                result.append("<li class='list-group-item'> Email: ").append(student.getsEmail()).append("</li>");
            }
            result.append("</ul>");
            out.println(result);
        }catch (SQLException sqle) {
            out.println("<div class='alert alert-danger' role='alert'>Error al conectar con la base de datos</div>");
            sqle.printStackTrace();
        }
    }
}
