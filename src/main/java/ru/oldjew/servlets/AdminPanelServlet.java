package ru.oldjew.servlets;

import ru.oldjew.repositories.UsersRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/adminPanel")
public class AdminPanelServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersRepositoryImpl usersRepository = new UsersRepositoryImpl();
        req.setAttribute("usersFromDB", usersRepository.findAll());
        req.getServletContext().getRequestDispatcher("/jsp/adminpanel.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersRepositoryImpl usersRepository = new UsersRepositoryImpl();
        String roleFromPage = (String) req.getParameter("roleSelect");
        int userId = Integer.parseInt(req.getParameter("userId"));
        usersRepository.ManageRole(userId, roleFromPage);
        resp.sendRedirect(req.getContextPath() + "/adminPanel");
    }
}
