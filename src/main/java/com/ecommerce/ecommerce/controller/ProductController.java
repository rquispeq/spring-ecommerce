/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.service.ProductService;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Oghma
 */

@Controller
@RequestMapping("/products")
public class ProductController {
    
    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    
    @Autowired
    private ProductService productService;
    
    @GetMapping("")
    public String show(Model model){
        model.addAttribute("products", productService.getAll());
        return "products/show";
    }
    
    @GetMapping("/create")
    public String create(){
        return "products/create";
    }
    
    @PostMapping("/save")
    private String save(Product product){
        LOGGER.info("este es el objeto producto {}", product);
        User user = new User(1, "", "", "", "", "", "", "");
        product.setUser(user);
        productService.save(product);
        return "redirect:/products";
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model){
        Product product = new Product();
        Optional<Product> optionalProduct = productService.get(id);
        product = optionalProduct.get();
        model.addAttribute("product", product);
        return "products/edit";
    }
    
    @PostMapping("/update")
    public String update(Product product){
        productService.update(product);
        return "redirect:/products";
    }
    
}
