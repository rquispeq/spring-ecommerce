/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.Order;
import com.ecommerce.ecommerce.model.User;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Oghma
 */
public interface OrderService {
    Order save (Order order);
    
    List<Order> findAll();

    public String generateOrderNumber();
    
    public List<Order> findByUser(User user);
    
    public Optional<Order> get(Integer id);
}
