package ru.oldjew.servlets;

import ru.oldjew.repositories.UsersRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/signUp")
public class SignUpServlet extends HttpServlet {
    private UsersRepositoryImpl usersRepository;

    @Override
    public void init() throws ServletException {
        this.usersRepository = new UsersRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getServletContext().getRequestDispatcher("/jsp/signUp.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String userName = req.getParameter("username");
        String password = req.getParameter("password");
        if (usersRepository.saveUser(userName, password, "worker")) {
            req.setAttribute("userByName", usersRepository.getByName(userName));
            resp.sendRedirect(req.getContextPath() + "/home");
        } else {
            req.setAttribute("userByName", null);
            req.setAttribute("note","Already exists!");
            resp.sendRedirect(req.getContextPath() + "/signUp");
        }

    }
}
