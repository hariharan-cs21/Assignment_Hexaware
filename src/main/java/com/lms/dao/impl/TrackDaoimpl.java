package com.lms.dao.impl;

import com.lms.dao.TrackDao;
import com.lms.model.Track;
import com.lms.utility.DbUtility;

import java.sql.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TrackDaoimpl implements TrackDao {
    DbUtility db=DbUtility.getInstance();
    public void insert(Track track) throws SQLException {

        Connection con = db.connect();
        String sql = "insert into track(id,name) values (?,?)";
        try {
            PreparedStatement pstmt = con.prepareStatement(sql);
            pstmt.setInt(1, track.getTrackId());
            pstmt.setString(2, track.getName());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        db.close(con);
    }

    @Override
    public List<Track> getAll() throws SQLException {
        Connection con=db.connect();
        String sql="select * from track";
        List<Track>list=new ArrayList<>();
        ResultSet res=null;
        try{
            PreparedStatement ps=con.prepareStatement(sql);
            res=ps.executeQuery();
            while(res.next()){
                Track track=new Track(res.getInt("id"),res.getString("name"));
                list.add(track);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        db.close(con);
        return list;
    }
}
