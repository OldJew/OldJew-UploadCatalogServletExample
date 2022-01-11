package ru.oldjew.Utils;

import at.favre.lib.crypto.bcrypt.BCrypt;
import ru.oldjew.repositories.User;

public class BCryptUtil {

    public static String generateHash(String password){
        return BCrypt.withDefaults().hashToString(12, password.toCharArray());
    }

    public static boolean checkHash(String password, User user){
        BCrypt.Result result = BCrypt.verifyer().verify(password.toCharArray(), user.getPassword());
        return result.verified;
    }
}
