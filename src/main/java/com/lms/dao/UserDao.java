package com.lms.dao;

import com.lms.exception.UserNotFoundException;
import com.lms.model.User;

import java.sql.SQLException;

public interface UserDao {
    Integer login(String username, String password) throws UserNotFoundException, SQLException;

    void signup(User user) throws SQLException;
    User getUserByUsername(String username) throws SQLException;
}
