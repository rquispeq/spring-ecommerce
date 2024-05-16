/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import java.util.Date;

/**
 *
 * @author Oghma
 */

@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_order;
    private String number;
    private Date created_date;
    private Date receive_date;
    private double total;
    
    @ManyToOne
    private User user;
    
    @OneToOne(mappedBy = "order")
    private DetailOrder detail;

    public Order() {
    }

    public Order(int id_order, String number, Date created_date, Date receive_date, double total) {
        this.id_order = id_order;
        this.number = number;
        this.created_date = created_date;
        this.receive_date = receive_date;
        this.total = total;
    }

    public int getId_order() {
        return id_order;
    }

    public void setId_order(int id_order) {
        this.id_order = id_order;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }

    public Date getReceive_date() {
        return receive_date;
    }

    public void setReceive_date(Date receive_date) {
        this.receive_date = receive_date;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public DetailOrder getDetail() {
        return detail;
    }

    public void setDetail(DetailOrder detail) {
        this.detail = detail;
    }
    

    @Override
    public String toString() {
        return "Order{" + "id_order=" + id_order + ", number=" + number + ", created_date=" + created_date + ", receive_date=" + receive_date + ", total=" + total + '}';
    }
    
    
}
