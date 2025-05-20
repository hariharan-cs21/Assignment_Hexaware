package com.lms.dao;

import com.lms.exception.*;
import com.lms.model.Learner;

import java.sql.SQLException;
import java.util.List;

public interface LearnerDao {
    /* crud operation*/
    List<Learner> getAll() throws SQLException;
    Learner getById(int id) throws InvalidIdException, SQLException;
    void deleteById(int id) throws InvalidIdException, SQLException;
    Learner update(int id,Learner learner) throws InvalidIdException, InvalidInputException, SQLException;
    void insert(Learner learner,int id) throws InvalidInputException, SQLException;
}
