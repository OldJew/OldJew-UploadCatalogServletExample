package ru.oldjew.Utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.security.Principal;

public class UserRoleRequestWrapperUtils extends HttpServletRequestWrapper {

    private String user;
    private String role;
    private HttpServletRequest realRequest;

    public UserRoleRequestWrapperUtils(HttpServletRequest request, String user, String role) {
        super(request);
        this.user = user;
        this.role = role;
        this.realRequest = request;
    }

    @Override
    public boolean isUserInRole(String role) {
        if (role == null){
            return realRequest.isUserInRole(role);
        }
        if (this.role == role){
            return true;
        } else return false;
    }

    @Override
    public Principal getUserPrincipal() {
        if (user == null){
            return realRequest.getUserPrincipal();
        }
        return new Principal() {
            @Override
            public String getName() {
                return user;
            }
        };
    }
}
