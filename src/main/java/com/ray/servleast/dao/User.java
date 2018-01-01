package com.ray.servleast.dao;

import com.ray.servleast.model.Role;

import javax.naming.InitialContext;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Properties;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by Ray on 2017/12/31.
 */
public class User {

    private static ConcurrentHashMap<String, User> map = new ConcurrentHashMap<>();
    private static Properties sqlStatements = new Properties();
    private static InitialContext context;
    private static DataSource dataSource;

    private Long id;
    private String username;
    private String password;

    private List<Role> roles;

    public User(Long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Role> getRoles() {
        return roles;
    }

    public void setRoles(List<Role> roles) {
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public static void setSQL(Properties properties) {
        sqlStatements = properties;
    }

    public static Optional<User> get(String username) {
        Optional<User> userOptional = Optional.empty();
        if (context == null) {
            try {
                context = new InitialContext();
                dataSource = (DataSource) context.lookup("java:/comp/env/jdbc/TestDB");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (sqlStatements != null) {
            User savedUser = map.getOrDefault(username, null);
            if (savedUser != null) {
                userOptional = Optional.of(savedUser);
            } else {
                try {
                    Connection connection = dataSource.getConnection();
                    PreparedStatement statement = connection.prepareStatement(sqlStatements.getProperty("getUserById"));
                    statement.setString(1, username);
                    ResultSet resultSet = statement.executeQuery();
                    if (resultSet.next()) {
                        Long userId = resultSet.getLong(1);
                        String password = resultSet.getString(2);
                        User unsavedUser = new User(userId, username, password);
                        statement = connection.prepareStatement(sqlStatements.getProperty("getRolesByUserId"));
                        statement.setLong(1, userId);
                        resultSet = statement.executeQuery();
                        List<Role> roles = new ArrayList<>();
                        String roleName;
                        while (resultSet.next()) {
                            roleName = resultSet.getString(1);
                            roles.add(Role.valueOf(roleName));
                        }
                        unsavedUser.setRoles(roles);
                        map.putIfAbsent(username, unsavedUser);
                        userOptional = Optional.of(unsavedUser);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        return userOptional;
    }
}
