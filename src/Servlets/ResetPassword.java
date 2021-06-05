package Servlets;

import Hibernate.Users;
import MigrateServlets.Alert;
import dao.UsersDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/ResetPassword")
public class ResetPassword extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String email = request.getParameter("email");
        String pass = request.getParameter("pass");

        UsersDAO udao = new UsersDAO();
        Users u = udao.find(email);
        u.setPassword(pass);
        if (u.getId() > 0 && u.getId() < 3000000) {
            udao.update(u);
        } else if (u.getId() > 3000000 && u.getId() < 5000000) {
            udao.update(u);
        } else if (u.getId() > 5000000 && u.getId() < 9999999) {
            udao.update(u);
        }

        RequestDispatcher rd = request.getRequestDispatcher("Login.jsp");
        rd.include(request, response);
        PrintWriter out = response.getWriter();
        Alert a = new Alert();
        out.println(a.successAlert("Password reset successfully"));

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        doPost(req, resp);
    }

}
