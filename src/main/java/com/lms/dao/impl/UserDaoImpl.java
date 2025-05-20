package com.lms.dao.impl;

import com.lms.enums.Role;
import com.lms.utility.DbUtility;
import com.lms.dao.UserDao;
import com.lms.exception.UserNotFoundException;
import com.lms.model.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDaoImpl implements UserDao {
    DbUtility db=DbUtility.getInstance();
    @Override
    public Integer login(String username, String password) throws UserNotFoundException, SQLException {
        Connection con=db.connect();
        String query="select * from user where username=? and password=?";
        try{
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,username);
            ps.setString(2,password);
            ResultSet rs=ps.executeQuery();
            if(rs.next()) return 1;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        db.close(con);
        return 0;
    }

    @Override
    public void signup(User user) throws SQLException {
        Connection con=db.connect();
        String query="insert into user(username,password,role) values(?,?,?)";
        try{
            PreparedStatement ps=con.prepareStatement(query);
            ps.setString(1,user.getUsername());
            ps.setString(2,user.getPassword());
            ps.setString(3,user.getRole().toString());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        db.close(con);
    }

    @Override
    public User getUserByUsername(String username) throws SQLException {
        Connection con=db.connect();
        String query="select * from user where username=?";
        PreparedStatement ps=con.prepareStatement(query);
        ps.setString(1,username);
        ResultSet rs=ps.executeQuery();
        User user=new User();
        if(rs.next()) {
            user.setId(rs.getInt("id"));
            user.setUsername(username);
            user.setPassword(rs.getString("password"));
            user.setRole(Role.valueOf(rs.getString("role")));
            return user;
        }

        return null;
    }
}
