package com.ecommerce.dao;

import com.ecommerce.model.*;

import java.sql.SQLException;
import java.util.List;

public interface ProductDao {
    void insertProduct(Product product,int id) throws SQLException;
    List<Product> fetchProductById(int id) throws SQLException;

}
