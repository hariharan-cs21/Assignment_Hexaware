package com.ecommerce.controller;

import com.ecommerce.dao.ProductDao;
import com.ecommerce.dao.PurchaseDao;
import com.ecommerce.daoImpl.ProductDaoImpl;
import com.ecommerce.daoImpl.PurchaseDaoImpl;
import com.ecommerce.model.Customer;
import com.ecommerce.model.Product;
import com.ecommerce.model.Purchase;
import com.ecommerce.service.ProductService;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        ProductService productService = new ProductService();
        PurchaseDao purchaseDao=new PurchaseDaoImpl();
        while (true) {
            System.out.println("1. Insert Product");
            System.out.println("2. Fetch Products By Category Id");
            System.out.println("3. Insert Purchase");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine();

            switch (choice) {
                case 1:
                    try {
                        System.out.print("Enter Product ID: ");
                        int id = sc.nextInt();
                        sc.nextLine();
                        System.out.print("Enter Product Name: ");
                        String name = sc.nextLine();
                        System.out.print("Enter Product Price: ");
                        double price = sc.nextDouble();
                        sc.nextLine();
                        System.out.print("Enter Product Description: ");
                        String desc = sc.nextLine();
                        System.out.print("Enter Category ID: ");
                        int catId = sc.nextInt();

                        Product product = new Product();
                        product.setId(id);
                        product.setName(name);
                        product.setPrice(price);
                        product.setDescription(desc);
                        productService.insert(product, catId);
                        System.out.println("Product inserted successfully!");
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Enter Category Id: ");
                    int catId=sc.nextInt();
                    sc.nextLine();
                    List<Product>list=productService.fetchById(catId);
                    list.stream().forEach(l-> System.out.println(l));
                    break;
                case 3:
                    try {



                        System.out.print("Enter Customer ID: ");
                        int customerId = sc.nextInt();

                        System.out.print("Enter Product ID: ");
                        int productId = sc.nextInt();

                        Purchase purchase = new Purchase();
                        Customer customer = new Customer();
                        customer.setId(customerId);
                        purchase.setCustomer(customer);
                        Product product = new Product();
                        product.setId(productId);
                        purchase.setProduct(product);

                        purchaseDao.insertPurchase(purchase);
                        System.out.println("Purchase inserted successfully");
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    return;

            }
        }

    }
}

