package ru.oldjew.servlets;

import ru.oldjew.repositories.User;
import ru.oldjew.repositories.UsersRepositoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/signIn")
public class SignInServlet extends HttpServlet {
    private UsersRepositoryImpl usersRepository;

    @Override
    public void init() throws ServletException {
        usersRepository = new UsersRepositoryImpl();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.sendRedirect(req.getContextPath() + "/home");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("username");
        String password = req.getParameter("password");
        if (usersRepository.getByName(name)!= null){
            User user = usersRepository.getByName(name);
            if (user.getPassword().equals(password)){
                HttpSession session = req.getSession();
                session.setAttribute("loginedUser", user);
                session.setAttribute("role", user.getRole());
                resp.sendRedirect(req.getContextPath() + "/home");
            } else resp.sendRedirect(req.getContextPath() + "/home");
        } else resp.sendRedirect(req.getContextPath() + "/signUp");
    }
}
