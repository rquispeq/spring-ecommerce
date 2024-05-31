/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.DetailOrder;
import com.ecommerce.ecommerce.model.Order;
import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Oghma
 */
@Controller
@RequestMapping("/")
public class HomeController {
    
    private final Logger Log = LoggerFactory.getLogger(HomeController.class);
    
    @Autowired
    private ProductService productService;
    
    List<DetailOrder> details = new ArrayList<DetailOrder>();
    
    Order order = new Order();
    
    @GetMapping("")
    public String home(Model model){
        model.addAttribute("products", productService.getAll());
        return "user/home";
    }
    
    @GetMapping("producthome/{idProduct}")
    public String productHome(@PathVariable Integer idProduct, Model model){
        
        model.addAttribute("product", productService.get(idProduct).get());
        return "user/producthome";
    }
    
    @PostMapping("/cart")
    public String addCart(@RequestParam Integer idProduct, @RequestParam Integer amount){
        
        DetailOrder detailOrder = new DetailOrder();
        
        Product product = new Product();
        
        double sumTotal = 0;
        
       
        Optional<Product> productTarget = productService.get(idProduct);
        
        return "user/cart";
    }
}
