package com.svalero.academia.servlet;

import com.svalero.academia.dao.Database;
import com.svalero.academia.dao.StudentDao;
import com.svalero.academia.domain.Student;
import com.svalero.academia.domain.User;
import com.svalero.academia.exception.StudentAlreadyExistsException;
import com.svalero.academia.exception.StudentDoesNotExistException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

@WebServlet("/add-modify-student")
public class AddModifyStudentServlet extends HttpServlet {

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        PrintWriter out = resp.getWriter();

        User currentUser = (User) req.getSession().getAttribute("currentUser");
        if (currentUser == null) {
            resp.sendRedirect("roleDenied.jsp");
        }

        Integer idStudent = Integer.parseInt(req.getParameter("idStudent"));
        String sName = req.getParameter("sName");
        String sLastName = req.getParameter("sLastName");
        String sAddress = req.getParameter("sAddress");
        String sTlp = req.getParameter("sTlp");
        String sEmail = req.getParameter("sEmail");
        String action = req.getParameter("action");
        Student student = new Student(idStudent, sName, sLastName, sAddress, sTlp, sEmail);

        Database database = new Database();
        StudentDao studentDao = new StudentDao(database.getConnection());

        try{
            if (action.equals("register")) {
                studentDao.addStudent(student);
                //TODO COMPROBAR SI FUNCIONA BIEN ASI, arreglar con comillas simples en \"
                out.println("<div class='alert alert-success' role='alert'>\n" +
                        "  <h4 class='alert-heading'>Registro correcto</h4>\n" +
                        "  <p>El alumno se ha registrado de forma correcta.</p>\n" +
                        "  <hr>\n" +
                        "  <p class='mb-0'><a href='/academia-servlet/index.jsp'>Volver al menú</a></p>\n" +
                        "</div>");
            }else {
                studentDao.modifyStudentById(student, idStudent);
                out.println("<div class='alert alert-success' role='alert'>\n" +
                        "  <h4 class='alert-heading'>Modificación correcta</h4>\n" +
                        "  <p>El alumno se ha modificado de forma correcta.</p>\n" +
                        "  <hr>\n" +
                        "  <p class='mb-0'><a href='/academia-servlet/index.jsp'>Volver al menú</a></p>\n" +
                        "</div>");
            }

        }catch (SQLException sqle){
            out.println("<div class='alert alert-danger' role='alert'>Se ha producido un error al conectar con la base de datos</div>");
            sqle.printStackTrace();
        }catch (StudentAlreadyExistsException saee) {
            out.println("<div class='alert alert-danger' role='alert'>Ya existe este estudiante</div>");
            saee.printStackTrace();
        }catch (StudentDoesNotExistException sdne) {
            out.println("<div class='alert alert-danger' role='alert'>El estudiante que intentas modificar no existe</div>");
            sdne.printStackTrace();
        }
    }
}
