package com.lms.dao;
import com.lms.model.Track;

import java.sql.SQLException;
import java.util.List;

public interface TrackDao {
    void insert(Track track) throws SQLException;
    List<Track> getAll() throws SQLException;

}
