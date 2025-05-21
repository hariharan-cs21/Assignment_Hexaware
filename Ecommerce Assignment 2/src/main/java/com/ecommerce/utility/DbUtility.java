package com.ecommerce.utility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbUtility {
    private String url="jdbc:mysql://localhost:3306/mydb?useSSL=false";
    private String user="root";
    private String password="pass";
    private  String driver="com.mysql.cj.jdbc.Driver";
    private static Connection con;
    private DbUtility(){}
    private static DbUtility db=new DbUtility();
    public static DbUtility getInstance(){
        return db;
    }
    public Connection connect() {
        try {
            Class.forName(driver);
        }
        catch (ClassNotFoundException e){
            System.out.println(e.getMessage());
        }
        try{
            if(con==null || con.isClosed()) con= DriverManager.getConnection(url,user,password);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return con;
    }
    public static void close() throws SQLException {
        if (con != null && !con.isClosed()) {
            con.close();
        }
    }
}
