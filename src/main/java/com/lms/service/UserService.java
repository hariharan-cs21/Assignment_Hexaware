package com.lms.service;

import com.lms.dao.UserDao;
import com.lms.dao.impl.UserDaoImpl;
import com.lms.enums.Role;
import com.lms.exception.InvalidInputException;
import com.lms.exception.UserNotFoundException;
import com.lms.model.User;

import java.sql.SQLException;

public class UserService {
    private UserDao userDao=new UserDaoImpl();
    public User login(String username,String password) throws UserNotFoundException, SQLException {
            Integer isValid=userDao.login(username,password);
            if(isValid==1){
                User user= userDao.getUserByUsername(username);
                if(user==null) throw new UserNotFoundException("Invalid Credentials");
                return user;
            }
            else throw new UserNotFoundException("Invalid Credentials");
    }
    public void signup(String username,String password,String role) throws SQLException, InvalidInputException {
        if (username == null ||  username.equals("null")) {
            throw new InvalidInputException("Learner details cannot be null");
        }
        if(password.trim().isEmpty())
            throw new InvalidInputException("Learner details cannot be empty");
        User user=new User();
        user.setRole(Role.valueOf(role));
        user.setUsername(username);
        user.setPassword(password);
        userDao.signup(user);
    }
}
