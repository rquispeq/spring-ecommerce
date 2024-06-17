/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.DetailOrder;
import com.ecommerce.ecommerce.model.Order;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.service.DetailOrderService;
import com.ecommerce.ecommerce.service.OrderService;
import com.ecommerce.ecommerce.service.UserService;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
    
    @Autowired
    private OrderService orderService;
    
    @Autowired
    private DetailOrderService detailOrderService;
    
    @Autowired
    private PasswordEncoder passEncode;
    
    @GetMapping("/register")
    public String create(){
        return "user/register";
    }
    
    @PostMapping("/save")
    public String save(User user){
        user.setType("user");
        user.setPassword(passEncode.encode(user.getPassword()));
        user.setEmail(user.getUsername());
        userService.save(user);
        return "redirect:/";
    }
    
    @GetMapping("/login")
    public String login(){
        System.out.println("login controller");
        return "user/login";
    }
    
    @GetMapping("access")
    public String access(User user, HttpSession session){
        System.out.println("access controller");
//        Optional<User> userDB = userService.findByEmail(user.getEmail());
        Optional<User> userDB = userService.findById(Integer.parseInt(session.getAttribute("idUser").toString()));
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
    
    @GetMapping("/shop")
    public String getShopping(Model model, HttpSession session){
        Integer idUser = Integer.parseInt(session.getAttribute("idUser").toString()) ;
        User user = userService.findById(idUser).get();
        List<Order> orders = orderService.findByUser(user);
        
        model.addAttribute("sessionUser", session.getAttribute("idUser") );
        model.addAttribute("orders", orders);
        return "user/shop";
    }
    
    @GetMapping("/detail/{idOrder}")
    public String getDetails(@PathVariable Integer idOrder, Model model, HttpSession session){
        
        Order order = orderService.get(idOrder).get();
        List<DetailOrder> detailsOrder = detailOrderService.getDetailsFromOrder(order);
        
        model.addAttribute("details", detailsOrder);
        return "user/detailshop";
    }
    
    @GetMapping("/logout")
    public String logout(HttpSession session){
        session.removeAttribute("idUser");
        return "redirect:/";
    }
    
}
