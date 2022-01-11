package ru.oldjew.servlets;

import ru.oldjew.Utils.SecurityUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String path = req.getServletContext().getInitParameter("PATH_TO_FILES");
        File dir = new File(path);
        List<File> filesList = Arrays.asList(dir.listFiles());
        //если пользователь не зарегистрирован, он может скачивать только .txt файлы, иначе любые
        if (!SecurityUtils.canDownloadOther(req)){
            filesList =filesList.stream().filter(file -> file.getName().contains(".txt")).collect(Collectors.toList());
        } else {
            filesList = Arrays.asList(dir.listFiles());
        }
        PrintWriter writer = resp.getWriter();
        req.setAttribute("filesFromServer",filesList);
        req.getServletContext().getRequestDispatcher("/jsp/home.jsp").forward(req, resp);

    }
}
