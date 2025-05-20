package com.lms.utility;

import com.lms.exception.InvalidIdException;
import com.lms.model.Learner;

import javax.xml.transform.Result;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.*;

public class LearnerUtility {





    public List<Learner> getAllData() throws SQLException {
        Connection con=DbUtility.connect();
        ResultSet rs=null;
        String query="select * from Learner";
        List<Learner>learnerList=new ArrayList<>();
        try {
            PreparedStatement ps = con.prepareStatement(query);
            rs = ps.executeQuery();
            while (rs.next()) {
                Learner learner = new Learner(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getString("email"),null);
                learnerList.add(learner);
            }
        }
        catch (SQLException e) {System.out.println(e.getMessage());}
        DbUtility.close(con);
        return learnerList;

    }
}
