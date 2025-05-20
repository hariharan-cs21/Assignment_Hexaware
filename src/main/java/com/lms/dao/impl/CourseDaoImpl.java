package com.lms.dao.impl;

import com.lms.dao.CourseDao;
import com.lms.exception.InvalidIdException;
import com.lms.model.Course;
import com.lms.model.Track;
import com.lms.utility.DbUtility;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CourseDaoImpl implements CourseDao {
    DbUtility db=DbUtility.getInstance();
    @Override
    public void insert(Course course, int trackId) throws SQLException {
        Connection con=db.connect();

        try{
            String query="insert into course values(?,?,?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1,course.getId());
            ps.setString(2,course.getTitle());
            ps.setDouble(3,course.getFee());
            ps.setDouble(4,course.getDisount());
            ps.setString(5,course.getPublish_date().toString());
            ps.setInt(6,trackId);
            ps.executeUpdate();

        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        db.close(con);

    }

    @Override
    public List<Course> getAll() throws SQLException {
        Connection con=db.connect();
        String sql=" select c.id ,c.title,c.fee,c.discount,c.publish_Date,t.name from course c join track t on c.track_id = t.id";
        List<Course>list=new ArrayList<>();
        ResultSet res=null;
        try{
            PreparedStatement ps=con.prepareStatement(sql);
            res=ps.executeQuery();
            while(res.next()){
               Course course =new Course();
                course.setId(res.getInt("id"));
                course.setTitle(res.getString("title"));
               course.setFee(res.getDouble("fee"));
               course.setDisount(res.getDouble("discount"));
               course.setPublish_date(res.getDate("publish_date").toLocalDate());
               Track tr=new Track();
               tr.setName(res.getString("name"));
               course.setTrack(tr);
               list.add(course);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        db.close(con);
        return list;
    }

    @Override
    public List<Course> getCourseByTrackId(int id) throws InvalidIdException, SQLException {
        Connection con=db.connect();
        String sql=" select * from course c join track t on c.track_id=t.id where track_id=?";
        List<Course>list=new ArrayList<>();
        ResultSet res=null;
        try{
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            res=ps.executeQuery();
            while(res.next()){
                Course course =new Course();
                course.setId(res.getInt("id"));
                course.setTitle(res.getString("title"));
                course.setFee(res.getDouble("fee"));
                course.setDisount(res.getDouble("discount"));
                course.setPublish_date(res.getDate("publish_date").toLocalDate());
                Track tr=new Track();
                tr.setName(res.getString("name"));
                course.setTrack(tr);
                list.add(course);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        db.close(con);
        return list;
    }
    public Course getById(int id) throws SQLException, InvalidIdException {
        Connection con=db.connect();
        String sql="select * from course where id=?";
        ResultSet res=null;
        Course course =new Course();
        try{
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            res=ps.executeQuery();
            if(res.next()){
                course.setId(res.getInt("id"));
                course.setTitle(res.getString("title"));
                course.setFee(res.getDouble("fee"));
                course.setDisount(res.getDouble("discount"));
                course.setPublish_date(res.getDate("publish_date").toLocalDate());
                            }
            else throw new InvalidIdException("Course Id not found");
        } catch (InvalidIdException e) {
            throw new InvalidIdException(e.getMessage());
        } finally {
            db.close(con);
        }

        return course;
    }

}
