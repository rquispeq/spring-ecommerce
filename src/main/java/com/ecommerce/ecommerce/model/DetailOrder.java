/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.model;

/**
 *
 * @author Oghma
 */
public class DetailOrder {
    private int idDetailOrder;
    private String name;
    private double amount;
    private double price;
    private double total;

    public DetailOrder() {
    }

    public DetailOrder(int idDetailOrder, String name, double amount, double price, double total) {
        this.idDetailOrder = idDetailOrder;
        this.name = name;
        this.amount = amount;
        this.price = price;
        this.total = total;
    }

    public int getIdDetailOrder() {
        return idDetailOrder;
    }

    public void setIdDetailOrder(int idDetailOrder) {
        this.idDetailOrder = idDetailOrder;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "DetailOrder{" + "idDetailOrder=" + idDetailOrder + ", name=" + name + ", amount=" + amount + ", price=" + price + ", total=" + total + '}';
    }
    
}
