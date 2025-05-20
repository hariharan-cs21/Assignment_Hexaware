package com.lms.dao;

import com.lms.exception.InvalidIdException;
import com.lms.model.Course;

import java.sql.SQLException;
import java.util.List;

public interface CourseDao {
    void insert(Course course, int trackId) throws SQLException;
    List<Course> getAll() throws SQLException;
    List<Course> getCourseByTrackId(int id) throws InvalidIdException, SQLException;
    Course getById(int id) throws InvalidIdException, SQLException;
}
