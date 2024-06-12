/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.User;
import java.util.Optional;

/**
 *
 * @author Oghma
 */
public interface UserService {
    Optional<User> findById(Integer id);
    
    public User save(User user);
    
    public Optional<User> findByEmail(String email);
}
