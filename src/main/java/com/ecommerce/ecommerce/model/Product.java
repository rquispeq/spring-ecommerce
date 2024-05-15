/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.model;

/**
 *
 * @author Oghma
 */
public class Product {
    private int id_product;
    private String name;
    private String description;
    private String image;
    private double price;
    private int amount;

    public Product() {
    }

    public Product(int id_product, String name, String description, String image, double price, int amount) {
        this.id_product = id_product;
        this.name = name;
        this.description = description;
        this.image = image;
        this.price = price;
        this.amount = amount;
    }

    public int getId_product() {
        return id_product;
    }

    public void setId_product(int id_product) {
        this.id_product = id_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Product{" + "id_product=" + id_product + ", name=" + name + ", description=" + description + ", image=" + image + ", price=" + price + ", amount=" + amount + '}';
    }
    
    
}
