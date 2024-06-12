/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Oghma
 */

@Controller
@RequestMapping("user")
public class UserController {
    
    @Autowired
    private UserService userService;
    
    @GetMapping("/register")
    public String create(){
        return "user/register";
    }
    
    @PostMapping("/save")
    public String save(User user){
        user.setType("user");
        userService.save(user);
        return "redirect:/";
    }
    
    @GetMapping("/login")
    public String login(){
        
        return "user/login";
    }
    
    @PostMapping("access")
    public String access(User user, HttpSession session){
        Optional<User> userDB = userService.findByEmail(user.getEmail());
        if(userDB.isPresent()){
            session.setAttribute("idUser", userDB.get().getId_user());
            
            if(userDB.get().getType().equals("admin")){
                return "redirect:/admin";
            }
            
            return "redirect:/";
        } else {
            return "user/notfound";
        }
        
    }
}
