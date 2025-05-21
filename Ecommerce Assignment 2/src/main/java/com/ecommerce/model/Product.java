package com.ecommerce.model;

public class Product {
    private int id;
    private String name;
    private double price;
    private String description;
    Catgeory catgeory;
    public Product() {
    }
    public Product(int id, String name, double price, String description, Catgeory catgeory) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.catgeory = catgeory;
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Catgeory getCatgeory() {
        return catgeory;
    }

    public void setCatgeory(Catgeory catgeory) {
        this.catgeory = catgeory;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", catgeory=" + catgeory +
                '}';
    }
}
