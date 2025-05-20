package com.lms.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtility {
    private static String url="jdbc:mysql://localhost:3306/lms_db?useSSL=false";
    private static String user="root";
    private static String password="pass";
    private static String driver="com.mysql.cj.jdbc.Driver";
    private static Connection con;
    private static  DbUtility db=new DbUtility();
    private DbUtility(){};
    public static DbUtility getInstance(){
        return db;
    }
    public static Connection connect() throws SQLException {
        //load driver
        try {
            Class.forName(driver);

        } catch (ClassNotFoundException e) {
            System.out.println(e.getMessage());
        }
        //establish connection
        try{
            if(con==null || con.isClosed()) con= DriverManager.getConnection(url,user,password);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }

    public static void close(Connection con) throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }
}
