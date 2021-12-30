package ru.oldjew.repositories;

import java.util.Map;

public interface UsersRepository {
    Map<Integer, User> findAll();
    boolean saveUser(String name, String password, String role);
    User getByName(String name);
}
