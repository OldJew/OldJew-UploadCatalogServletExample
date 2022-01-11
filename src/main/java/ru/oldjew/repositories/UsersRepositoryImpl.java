package ru.oldjew.repositories;

import at.favre.lib.crypto.bcrypt.BCrypt;

import java.util.HashMap;
import java.util.Map;

public class UsersRepositoryImpl implements UsersRepository {

    private int idAutoIncrement = FakeUserDatabase.database().usersDataBase().size() + 1;

    @Override
    public Map<Integer, User> findAll() {
        return FakeUserDatabase.database().usersDataBase();
    }

    @Override
    public boolean saveUser(String name, String password, String role) {
        for (Map.Entry<Integer, User> entry : FakeUserDatabase.database().usersDataBase().entrySet()) {
            if (entry.getValue().getName().equals(name)) {
                return false;
            }
        }
        FakeUserDatabase.database().usersDataBase().put(idAutoIncrement++, new User(name, password, role));
        return true;
    }
    @Override
    public User getByName(String name) {
        for (Map.Entry<Integer, User> entry : FakeUserDatabase.database().usersDataBase().entrySet()){
            if (entry.getValue().getName().equals(name)){
                return entry.getValue();
            }
        }
        return null;
    }
    public void ManageRole(Integer userId, String role){
        User user = FakeUserDatabase.database().usersDataBase().get(userId);
        user.setRole(role);
        FakeUserDatabase.database().usersDataBase().replace(userId,user);
    }
}
