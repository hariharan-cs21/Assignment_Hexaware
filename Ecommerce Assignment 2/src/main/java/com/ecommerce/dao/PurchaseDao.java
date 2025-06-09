package com.ecommerce.dao;

import com.ecommerce.exception.IdNotFoundException;
import com.ecommerce.model.Purchase;

import java.sql.SQLException;

public interface PurchaseDao {
    void insertPurchase(Purchase purchase) throws SQLException, IdNotFoundException;

    boolean productExists(int id) throws SQLException;
}
