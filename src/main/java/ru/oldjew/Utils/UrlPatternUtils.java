package ru.oldjew.Utils;

import javax.servlet.ServletContext;
import javax.servlet.ServletRegistration;
import javax.servlet.http.HttpServletRequest;
import java.util.Collection;
import java.util.Map;

public class UrlPatternUtils {

    private static boolean hasUrlPattern(ServletContext servletContext, String urlPattern){
        Map<String, ? extends ServletRegistration> map = servletContext.getServletRegistrations();

        for (String servletName : map.keySet()) {
            ServletRegistration sr = map.get(servletName);

            Collection<String> mappings = sr.getMappings();
            if (mappings.contains(urlPattern)){
                return true;
            }
        }
        return false;
    }


    public static String getUrlPattern(HttpServletRequest request){
        String urlPattern = null;
        if (request.getPathInfo() != null){
            urlPattern = request.getServletPath() + "/*";
            return urlPattern;
        }
        urlPattern = request.getServletPath();

        boolean hasPattern = hasUrlPattern(request.getServletContext(), urlPattern);
        if (hasPattern){
            return urlPattern;
        }

        int i = request.getServletPath().lastIndexOf('.');
        if (i != -1){
            String extension = request.getServletPath().substring(i + 1);
            urlPattern = "*." + extension;
            hasPattern = hasUrlPattern(request.getServletContext(), urlPattern);

            if (hasPattern){
                return urlPattern;
            }
        }
        return "/";
    }
}
