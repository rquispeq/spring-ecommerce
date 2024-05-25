/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.Product;
import java.util.List;
import java.util.Optional;

/**
 *
 * @author Oghma
 */
public interface ProductService {

    public Product save(Product product);

    public Optional<Product> get(int id);

    public void update(Product product);

    public void delete(int id);
    
    public List<Product> getAll();
}
