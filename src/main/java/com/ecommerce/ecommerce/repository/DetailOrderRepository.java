/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.ecommerce.repository;

import com.ecommerce.ecommerce.model.DetailOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Oghma
 */

@Repository
public interface DetailOrderRepository extends JpaRepository<DetailOrder, Integer> {
    
}
