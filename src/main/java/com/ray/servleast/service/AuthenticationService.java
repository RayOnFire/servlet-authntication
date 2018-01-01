package com.ray.servleast.service;

import com.ray.servleast.dao.User;

import javax.servlet.ServletContext;
import java.util.Optional;

/**
 * Created by Ray on 2017/12/31.
 */
public class AuthenticationService {
    public static boolean authenticate(ServletContext context, String username, String password) {
        Optional<User> savedUser = User.get(username);
        if (savedUser.isPresent() && savedUser.get().getPassword().equals(password)) {
            context.setAttribute("user", savedUser.get());
            return true;
        }
        return false;
    }
}
