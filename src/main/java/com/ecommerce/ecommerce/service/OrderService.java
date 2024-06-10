/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.Order;
import java.util.List;

/**
 *
 * @author Oghma
 */
public interface OrderService {
    Order save (Order order);
    
    List<Order> findAll();
}
