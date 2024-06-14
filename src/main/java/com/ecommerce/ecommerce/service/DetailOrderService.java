/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.DetailOrder;
import com.ecommerce.ecommerce.model.Order;
import java.util.List;

/**
 *
 * @author Oghma
 */


public interface DetailOrderService {
    
    DetailOrder save( DetailOrder detailOrder);
    
    List<DetailOrder> getDetailsFromOrder(Order order);
}
