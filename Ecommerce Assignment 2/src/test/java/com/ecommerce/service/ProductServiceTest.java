package com.ecommerce.service;

import com.ecommerce.model.Product;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class ProductServiceTest {
    @Test
    public void testInsertProduct() {
        ProductService productService = new ProductService();

        Product product = new Product();
        product.setId(101);
        product.setName("JUnitTestProduct");
        product.setPrice(99.99);
        product.setDescription("Test Desc");

        assertDoesNotThrow(() -> {
            productService.insert(product, 1);
        });
    }
    @Test
    public void testFetchProductsByCategoryId() throws  SQLException {
        ProductService productService = new ProductService();

        List<Product> products = productService.fetchById(1);

        assertFalse(products.isEmpty(),"Product should not be empty") ;
    }
}