package com.ecommerce.daoImpl;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.model.Catgeory;
import com.ecommerce.model.Product;
import com.ecommerce.utility.DbUtility;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductDaoImpl implements ProductDao {
    DbUtility db=DbUtility.getInstance();
    @Override
    public void insertProduct(Product product,int catId) throws SQLException {
        Connection con=db.connect();

        try{
            String query="insert into product values(?,?,?,?,?)";
            PreparedStatement ps=con.prepareStatement(query);
            ps.setInt(1,product.getId());
            ps.setString(2,product.getName());
            ps.setDouble(3,product.getPrice());
            ps.setString(4,product.getDescription());
            ps.setInt(5,catId);
            ps.executeUpdate();
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        db.close();
    }

    @Override
    public List<Product> fetchProductById(int id) throws SQLException {
        Connection con=db.connect();
        String sql=" select * from product p join category c on p.category_id=c.id where c.id=?";
        List<Product>list=new ArrayList<>();
        ResultSet res=null;
        try{
            PreparedStatement ps=con.prepareStatement(sql);
            ps.setInt(1,id);
            res=ps.executeQuery();
            while(res.next()){
                Product product =new Product();
                product.setId(res.getInt("id"));
                product.setName(res.getString("title"));
                product.setPrice(res.getDouble("price"));
                product.setDescription(res.getString("description"));
                Catgeory catgeory=new Catgeory();
                catgeory.setName(res.getString("id"));
                catgeory.setName(res.getString("name"));
                product.setCatgeory(catgeory);
                list.add(product);
            }
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
        }
        db.close();
        return list;

    }
}
