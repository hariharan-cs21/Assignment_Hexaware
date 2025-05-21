package com.ecommerce.service;


import com.ecommerce.exception.IdNotFoundException;
import org.junit.jupiter.api.Test;

import com.ecommerce.model.Customer;
import com.ecommerce.model.Product;
import com.ecommerce.model.Purchase;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PurchaseServiceTest {

    PurchaseService purchaseService = new PurchaseService();

    @Test
    void insertPurchase_WithInvalidIds_ThrowsIdNotFoundException() {
        //Throw Id not found exception if invalid id given in purchase
        Product product = new Product();
        product.setId(9999);

        Customer customer = new Customer();
        customer.setId(8888);

        Purchase purchase = new Purchase();
        purchase.setProduct(product);
        purchase.setCustomer(customer);

        assertThrows(IdNotFoundException.class, () -> {
            purchaseService.insert(purchase);
        });
    }
}
