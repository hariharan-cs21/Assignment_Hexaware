package com.lms.service;

import com.lms.dao.*;
import com.lms.dao.impl.*;
import com.lms.exception.InvalidIdException;
import com.lms.model.Course;
import com.lms.model.Track;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class CourseService {
    private CourseDao courseDao = new CourseDaoImpl();
    private TrackDao trackDao = new TrackDaoimpl();

    public void insertTrack(Track track) throws SQLException {
        int id = (int) (Math.random() * 10000000);
        track.setTrackId(id);
        trackDao.insert(track);
    }
    public void insertCourse(Course course,int trackid) throws SQLException {
        int id = (int) (Math.random() * 10000000);
        course.setId(id);
        course.setPublish_date(LocalDate.now());
        courseDao.insert(course,trackid);
    }

    public List<Track> getAllTracks() throws SQLException {
        return trackDao.getAll();
    }
    public List<Course> getAllCourse() throws SQLException {
        return courseDao.getAll();
    }
    public List<Course> getCourseByTrackId(int id) throws SQLException, InvalidIdException {
        return courseDao.getCourseByTrackId(id);
    }

}
