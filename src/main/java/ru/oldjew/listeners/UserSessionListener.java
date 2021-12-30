package ru.oldjew.listeners;

import ru.oldjew.repositories.User;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class UserSessionListener implements HttpSessionListener {
    @Override
    public void sessionCreated(HttpSessionEvent se) {
        System.out.println("Session created: ID=" + se.getSession().getId() + "   " + se.getSource());

    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        System.out.println("Session destroyed: ID=" + se.getSession().getId());
        if (se.getSession().getAttribute("loginedUser") != null){
            User user = (User) se.getSession().getAttribute("loginedUser");
            System.out.print(" User: " + user.getName() + " Role: " + user.getRole());
        }
    }
}
