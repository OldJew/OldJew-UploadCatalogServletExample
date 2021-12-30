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
        usersDataBase.put(1,new User("admin","admin","admin"));
        usersDataBase.put(2,new User("test","test","worker"));
        usersDataBase.put(3,new User("boris","britva","worker"));
    }

    public static FakeUserDatabase database(){
        return database;
    }

    public Map<Integer, User> usersDataBase(){
        return usersDataBase;
    }
}
