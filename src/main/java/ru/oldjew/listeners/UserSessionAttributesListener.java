package ru.oldjew.listeners;

import ru.oldjew.repositories.User;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

@WebListener
public class UserSessionAttributesListener implements HttpSessionAttributeListener {
    @Override
    public void attributeAdded(HttpSessionBindingEvent se) {
        if (se.getSession().getAttribute("loginedUser") != null){
            User user = (User) se.getSession().getAttribute("loginedUser");
            System.out.println(" User: " + user.getName() + " Role: " + user.getRole());
        }
    }

    @Override
    public void attributeRemoved(HttpSessionBindingEvent event) {
        HttpSessionAttributeListener.super.attributeRemoved(event);
    }
}
