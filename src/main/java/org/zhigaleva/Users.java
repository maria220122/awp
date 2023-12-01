package org.zhigaleva;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public  class Users {
    private static Map<Long, User> DB = new HashMap<>(Map.of(
            1L, new User(1L, "admin", "admin", "admin"),
            2L, new User(2L, "user", "user", "user")));

    public static User findByExample(User user) {
        return DB.values().stream()
                .filter(item -> user.getId() == null || item.getId().equals(user.getId()))
                .filter(item -> user.getName() == null || item.getName().equals(user.getName()))
                .filter(item -> user.getLogin() == null || item.getLogin().equals(user.getLogin()))
                .filter(item -> user.getPassword() == null || item.getPassword().equals(user.getPassword()))
                .findFirst()
                .orElse(null);
    }
}
