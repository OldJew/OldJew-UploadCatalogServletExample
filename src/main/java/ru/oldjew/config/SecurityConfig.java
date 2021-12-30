package ru.oldjew.config;

import java.util.*;

public class SecurityConfig {
    public static final String ROLE_ADMIN = "admin";
    public static final String ROLE_WORKER = "worker";

    private static final Map<String, List<String>> config = new HashMap<>();

    static {
        init();
    }

    private static void init(){

        //config admin role
        List<String> urlPatternsAdmin = new ArrayList<>();
        urlPatternsAdmin.add("/adminPanel");

        config.put(ROLE_ADMIN, urlPatternsAdmin);
        //config worker role
        config.put(ROLE_WORKER, null);
    }

    public static Set<String> getAllRoles(){
        return config.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role){
        return config.get(role);
    }
}
