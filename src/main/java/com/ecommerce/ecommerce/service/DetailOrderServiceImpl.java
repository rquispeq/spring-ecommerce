/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.DetailOrder;
import com.ecommerce.ecommerce.repository.DetailOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oghma
 */

@Service

public class DetailOrderServiceImpl implements DetailOrderService {
    
    @Autowired
    private DetailOrderRepository detailOrderRepository;

    @Override
    public DetailOrder save(DetailOrder detailOrder) {
        return detailOrderRepository.save(detailOrder);
    }
    
}
