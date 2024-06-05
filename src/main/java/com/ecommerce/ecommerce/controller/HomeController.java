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

    List<DetailOrder> cart = new ArrayList<DetailOrder>();

    Order order = new Order();

    @GetMapping("")
    public String home(Model model) {
        model.addAttribute("products", productService.getAll());
        return "user/home";
    }

    @GetMapping("producthome/{idProduct}")
    public String productHome(@PathVariable Integer idProduct, Model model) {

        model.addAttribute("product", productService.get(idProduct).get());
        return "user/producthome";
    }

    @PostMapping("/cart")
    public String addCart(@RequestParam Integer idProduct, @RequestParam Integer amount, Model model) {

        DetailOrder detailOrder = new DetailOrder();

        Product product = new Product();
        boolean productExistCart = false;

        double sumTotal = 0;

        Optional<Product> productTarget = productService.get(idProduct);
        product = productTarget.get();

        for (DetailOrder elementCart : cart) {
            if (elementCart.getProduct().getId_product() == idProduct) {
                productExistCart = true;
                elementCart.setAmount(elementCart.getAmount() + amount);
                elementCart.setTotal(elementCart.getAmount() * product.getPrice());
                break;
            }
        }

        if (!productExistCart) {
            detailOrder.setAmount(amount);
            detailOrder.setProduct(product);
            detailOrder.setName(product.getName());
            detailOrder.setPrice(product.getPrice());
            detailOrder.setTotal(product.getPrice() * amount);

            cart.add(detailOrder);
        }

        sumTotal = cart.stream().mapToDouble(dt -> dt.getTotal()).sum();
        order.setTotal(sumTotal);

        model.addAttribute("cart", cart);
        model.addAttribute("order", order);

        return "user/cart";
    }
    
    @GetMapping("/delete/cart/{idProduct}")
    public String removeFromCart(@PathVariable Integer idProduct, Model model){
        ArrayList<DetailOrder> newCart = new ArrayList<DetailOrder>();
        
        for(DetailOrder element: cart){
            if(element.getProduct().getId_product() != idProduct){
                newCart.add(element);
            }
        }
        
        cart = newCart;
        
        double sumTotal = cart.stream().mapToDouble(dt -> dt.getTotal()).sum();
        order.setTotal(sumTotal);
        
        model.addAttribute("cart", cart);
        model.addAttribute("order", order);
        
        return "user/cart";
    }
}
