package com.ecommerce.service;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.dao.PurchaseDao;
import com.ecommerce.daoImpl.ProductDaoImpl;
import com.ecommerce.daoImpl.PurchaseDaoImpl;
import com.ecommerce.exception.IdNotFoundException;
import com.ecommerce.model.Product;
import com.ecommerce.model.Purchase;

import java.sql.SQLException;
import java.util.List;

public class PurchaseService {
    PurchaseDao purchaseDao=new PurchaseDaoImpl();
    public void insert(Purchase purchase) throws SQLException, IdNotFoundException {
        if (!purchaseDao.productExists(purchase.getProduct().getId())) {
            throw new IdNotFoundException("Product with ID " + purchase.getProduct().getId() + " does not exist.");
        }
        purchaseDao.insertPurchase(purchase);

    }
}
