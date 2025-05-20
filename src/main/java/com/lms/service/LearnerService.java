package com.lms.service;

import com.lms.dao.LearnerDao;
import com.lms.dao.impl.LearnerDaoImpl;
import com.lms.exception.InvalidIdException;
import com.lms.exception.InvalidInputException;
import com.lms.model.Learner;

import java.sql.SQLException;
import java.util.List;

public class LearnerService {
    LearnerDao dao=new LearnerDaoImpl();
    public List<Learner> getAllLearners() throws SQLException {
        return dao.getAll();
    }
    public Learner getById(int id) throws InvalidIdException, SQLException {
        return dao.getById(id);
    }
    public void deleteById(int id) throws InvalidIdException, SQLException {
        dao.deleteById(id);
    }
    public Learner update(int id,Learner updatedLearner) throws InvalidInputException, InvalidIdException, SQLException {

        if (updatedLearner.getName() == null || updatedLearner.getEmail() == null || updatedLearner.getName().equals("null")) {
            throw new InvalidInputException("Learner details cannot be null");
        }
        if(updatedLearner.getName().trim().isEmpty() || updatedLearner.getEmail().trim().isEmpty())
            throw new InvalidInputException("Learner details cannot be empty");
        return dao.update(id,updatedLearner);
    }


    public void insert(Learner learner) throws InvalidInputException, SQLException {
        if (learner.getName() == null || learner.getEmail() == null || learner.getName().equals("null")) {
            throw new InvalidInputException("Learner details cannot be null");
        }
        if(learner.getName().trim().isEmpty() || learner.getEmail().trim().isEmpty())
            throw new InvalidInputException("Learner details cannot be empty");
        int id=(int)(Math.random()*10000);
        dao.insert(learner,id);
    }
}
