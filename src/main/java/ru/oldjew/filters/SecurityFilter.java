package ru.oldjew.filters;

import ru.oldjew.Utils.SecurityUtils;
import ru.oldjew.Utils.UserRoleRequestWrapperUtils;
import ru.oldjew.repositories.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebFilter("/*")
public class SecurityFilter implements Filter {
    public SecurityFilter() {
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest req = (HttpServletRequest) servletRequest;
        HttpServletResponse resp = (HttpServletResponse) servletResponse;


        User loginedUser =(User) req.getSession().getAttribute("loginedUser");

        HttpServletRequest wrappedReq = req;

        if (loginedUser != null){
            String name = loginedUser.getName();
            String role = loginedUser.getRole();
            wrappedReq = new UserRoleRequestWrapperUtils(req, name, role);
        }

        if (SecurityUtils.isSecurityPage(req)){
            if (loginedUser == null){
                resp.sendRedirect(wrappedReq.getContextPath() + "/signUp");
                return;
            }
            boolean isAllowed = SecurityUtils.isAllowed(wrappedReq);
            if (!isAllowed){
                req.getServletContext().getRequestDispatcher("/jsp/accessDenied.jsp").forward(req, resp);
                return;
            }

        }
        filterChain.doFilter(wrappedReq, resp);
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void destroy() {

    }
}
