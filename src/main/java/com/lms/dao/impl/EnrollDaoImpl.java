package com.lms.dao.impl;

import com.lms.dao.EnrollDao;
import com.lms.exception.InvalidIdException;
import com.lms.model.Enroll;
import com.lms.utility.DbUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class EnrollDaoImpl implements EnrollDao {
    DbUtility db=DbUtility.getInstance();

    @Override
    public void enroll(Enroll enroll) throws  SQLException {
        Connection con=db.connect();
        try{
            String sql="insert into enroll values(?,?,?,?,?)";
            PreparedStatement ps=con.prepareStatement((sql));
            ps.setInt(1,enroll.getLearner().getId());
            ps.setInt(2,enroll.getCourse().getId());
            ps.setString(3,enroll.getDate_of_enroll().toString());
            ps.setString(4,enroll.getCoupone_used());
            ps.setString(5,(String.valueOf(enroll.getFee_paid())));
            ps.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        db.close(con);
    }
}
