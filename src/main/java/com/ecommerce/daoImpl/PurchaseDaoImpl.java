package com.ecommerce.daoImpl;

import com.ecommerce.dao.PurchaseDao;
import com.ecommerce.model.Purchase;
import com.ecommerce.utility.DbUtility;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class PurchaseDaoImpl implements PurchaseDao {
    DbUtility db=DbUtility.getInstance();
    @Override
    public void insertPurchase(Purchase purchase) throws SQLException {
        Connection con = db.connect();
        int id= (int) (Math.random()*1000);
        String query = "INSERT INTO purchase (id, date_of_purchase, customer_id, product_id) VALUES (?, ?, ?, ?)";

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.setDate(2, Date.valueOf(LocalDate.now()));
            ps.setInt(3, purchase.getCustomer().getId());
            ps.setInt(4, purchase.getProduct().getId());
            ps.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error inserting purchase: " + e.getMessage());
        } finally {
            db.close();
        }
    }
}
