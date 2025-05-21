package com.ecommerce.service;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.daoImpl.ProductDaoImpl;
import com.ecommerce.model.Product;

import java.sql.SQLException;
import java.util.List;

public class ProductService {
    ProductDao productDao=new ProductDaoImpl();
    public void insert(Product product,int id) throws SQLException {
        productDao.insertProduct(product,id);
    }
    public List<Product> fetchById(int id) throws SQLException {
        return productDao.fetchProductById(id);
    }
}
