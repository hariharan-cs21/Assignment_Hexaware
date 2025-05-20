package com.lms.dao.impl;

import com.lms.dao.LearnerDao;
import com.lms.exception.InvalidIdException;
import com.lms.exception.InvalidInputException;
import com.lms.model.Learner;
import com.lms.utility.DbUtility;
import com.lms.utility.LearnerUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class LearnerDaoImpl implements LearnerDao {
    DbUtility db=DbUtility.getInstance();

    LearnerUtility learnerUtility=new LearnerUtility();

    @Override
    public List<Learner> getAll() throws SQLException {
        return learnerUtility.getAllData();
    }

    @Override
    public Learner getById(int id) throws InvalidIdException, SQLException {
        Connection con=db.connect();
        ResultSet rs=null;
        Learner learner = new Learner();
        String query="select * from Learner where id =?";
        PreparedStatement ps = con.prepareStatement(query);
        ps.setInt(1, id);
        rs = ps.executeQuery();
        if (rs.next()) {
            learner.setId(rs.getInt("id"));
            learner.setName(rs.getString("name"));
            learner.setEmail(rs.getString("email"));
            return learner;
        }
        else throw new InvalidIdException("Invalid Id ");




    }

    @Override
    public void deleteById(int id) throws InvalidIdException, SQLException {
        Connection con=db.connect();
        String query="delete from enroll where learner_id =?";
        PreparedStatement ps1=con.prepareStatement(query);
        ps1.setInt(1,id);
        ps1.executeUpdate();
        query="delete from learner where id =?";
        PreparedStatement ps2=con.prepareStatement(query);
        ps2.setInt(1,id);
        int deletedRow=ps2.executeUpdate();
        if(deletedRow==0) throw new InvalidIdException("Id not found");
    }

    @Override
    public Learner update(int id, Learner learner) throws InvalidIdException, InvalidInputException, SQLException {
        List<Learner>learnerList=getAll();
        Connection con=db.connect();
        String query="update learner set name=?,email=? where id=?";
        PreparedStatement ps=con.prepareStatement(query);

        ps.setString(1,learner.getName());
        ps.setString(2,learner.getEmail());
        ps.setInt(3,id);
        int rows=ps.executeUpdate();
        if(rows==0)  throw new InvalidIdException("Invalid Id");

        return learner;


    }

    @Override
    public void insert(Learner learner,int id) throws InvalidInputException, SQLException {
        Connection con=db.connect();
        String query="insert into learner values (?,?,?)";
        try {
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1,id);
            ps.setString(2,learner.getName());
            ps.setString(3,learner.getEmail());
            ps.executeUpdate();
        } catch (SQLException e) {
            throw new InvalidInputException("Cannot insert");
        }
        DbUtility.close(con);

    }

}
