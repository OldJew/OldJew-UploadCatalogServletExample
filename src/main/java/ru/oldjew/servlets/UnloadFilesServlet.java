package ru.oldjew.servlets;

import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;

import java.util.Arrays;
import java.util.List;

@WebServlet("/unload")
public class UnloadFilesServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //создание PrintWriter для записи файла
        try(PrintWriter writer = resp.getWriter()){
            //получение пути к файлу и проверка его существования
            String fileName = req.getParameter("filename");
            String path = req.getServletContext().getInitParameter("PATH_TO_FILES") + fileName;
            File file = new File(path);
            if (file.exists()){
            //передача заголовка ответа
                resp.setContentType("application/octet-stream");
                resp.setContentLengthLong(file.length());
                resp.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName()+"\"");
                //запись файла пользователю
                FileInputStream inputStream = new FileInputStream(file);
                int i;
                while ((i = inputStream.read()) != -1){
                    writer.write(i);
                }
                inputStream.close();
                writer.close();
            } else System.out.println("Cannot access to file");

        }
    }
}
