/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.User;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oghma
 */

@Service
public class UserDetailService implements UserDetailsService{

    @Autowired
    private UserService userService;
    
    public PasswordEncoder bcrypt;
    
    @Autowired
    HttpSession session;
    
    @Autowired
    public UserDetailService(UserService userService ){
        this.userService = userService;
    }
    
    private Logger log = LoggerFactory.getLogger(UserDetailService.class);
            
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        System.out.println("loadUserByname: " + username);
        Optional<User> userO = userService.findByEmail(username);
        if(userO.isPresent()){
            session.setAttribute("idUser", userO.get().getId_user());
            User user = userO.get();
            System.out.println("User founded in DB: " + user);
            UserDetails u2;
            u2 = org.springframework.security.core.userdetails.User.builder().username(user.getName()).password(user.getPassword()).roles(user.getType()).build();
            return u2;
        } else {
            throw new UsernameNotFoundException("Usuario no encontrado");
        }
        
    }
    
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
    
}
