package ru.oldjew.Utils;

import ru.oldjew.config.SecurityConfig;
import ru.oldjew.repositories.User;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

public class SecurityUtils {

    public static boolean isSecurityPage(HttpServletRequest req) {
        String urlPattern = UrlPatternUtils.getUrlPattern(req);

        Set<String> roles = SecurityConfig.getAllRoles();

        for (String role : roles) {
            List<String> listPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (listPatterns != null && listPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isAllowed(HttpServletRequest req) {

        String urlPattern = UrlPatternUtils.getUrlPattern(req);

        Set<String> roles = SecurityConfig.getAllRoles();

        for (String role : roles) {
            if (!req.isUserInRole(role)) {
                continue;
            }
            List<String> listPatterns = SecurityConfig.getUrlPatternsForRole(role);
            if (listPatterns != null && listPatterns.contains(urlPattern)) {
                return true;
            }
        }
        return false;
    }

    public static boolean canDownloadOther(HttpServletRequest req){
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("loginedUser");
        if (user != null){
            return true;
        } else return false;
    }
}
