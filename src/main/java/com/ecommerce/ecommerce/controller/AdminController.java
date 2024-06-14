/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.DetailOrder;
import com.ecommerce.ecommerce.model.Order;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.service.OrderService;
import com.ecommerce.ecommerce.service.ProductService;
import com.ecommerce.ecommerce.service.UserService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Oghma
 */

@Controller
@RequestMapping("/admin")
public class AdminController {
    
    @Autowired
    private ProductService productService;
    
    @Autowired
    private UserService userService;
    
    @Autowired
    private OrderService orderService;
    
    @GetMapping("")
    public String home(Model model){
        
        List<Product> products = productService.getAll();
        model.addAttribute("products", products);
        
        return "admin/home";
    }
    
    @GetMapping("/users")
    public String users(Model model){
        List<User> users = userService.getAll();
        model.addAttribute("users", users);
        return "admin/users";
    }
    
    @GetMapping("/orders")
    public String orders(Model model){
        List<Order> orders = orderService.findAll();
        model.addAttribute("orders", orders);
        return "admin/orders";
    }
    
    @GetMapping("/details/{idOrder}")
    public String detailsOrder(@PathVariable Integer idOrder, Model model){
        Order order = orderService.get(idOrder).get();
        List<DetailOrder> details = order.getDetail();
        model.addAttribute("details", details);
        return "admin/detailorder";
    }
}
