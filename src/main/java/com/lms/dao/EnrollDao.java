package com.lms.dao;

import com.lms.exception.InvalidIdException;
import com.lms.model.Enroll;

import java.sql.SQLException;

public interface EnrollDao {
    public void enroll(Enroll enroll) throws InvalidIdException, SQLException;
}
