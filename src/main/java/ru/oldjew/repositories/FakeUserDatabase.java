package ru.oldjew.repositories;

import java.util.HashMap;
import java.util.Map;

public class FakeUserDatabase {

    private static final FakeUserDatabase database;

    static {
        database = new FakeUserDatabase();
    }

    private Map<Integer, User> usersDataBase;

    private FakeUserDatabase(){
        this.usersDataBase = new HashMap<>();
        usersDataBase.put(1,new User("admin","$2a$12$nKi6Yhy/3buoFJSKFbekOOn75JeV4pje5uR3BUTNwbYCkFto2HpaS","admin"));
        usersDataBase.put(2,new User("test","$2a$12$UxD7AFSjtDvUBMN5ay.UDuuprIlOy14uA58hoby2jIBQr6neleFh.","worker"));
        usersDataBase.put(3,new User("boris","$2a$12$Dz7ZF8sPhNXMomDFJm7V3e/0JHzFEUfLTc8/wpnkiHrIDYFuJRYEu","worker"));
    }

    public static FakeUserDatabase database(){
        return database;
    }

    public Map<Integer, User> usersDataBase(){
        return usersDataBase;
    }
}
