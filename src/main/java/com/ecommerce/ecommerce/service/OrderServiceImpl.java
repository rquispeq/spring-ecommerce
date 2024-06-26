/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.service;

import com.ecommerce.ecommerce.model.Order;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.repository.OrderRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Oghma
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public List<Order> findAll() {
        return orderRepository.findAll();
    }

    public String generateOrderNumber() {
        int number = 0;

        String stringNumber = "";

        List<Order> orders = findAll();

        List<Integer> numbers = new ArrayList<Integer>();

        orders.stream().forEach(order -> numbers.add(Integer.parseInt(order.getNumber())));

        number = 1;
        if (!orders.isEmpty()) {
            number = numbers.stream().max(Integer::compare).get();
            number++;
        }

        if (number < 10) {
            stringNumber = "0000000000" + String.valueOf(number);
        }

        System.out.println("numero de orden generado: " + stringNumber);
        return stringNumber;
    }

    @Override
    public List<Order> findByUser(User user) {
        return orderRepository.findByUser(user);
    }

    @Override
    public Optional<Order> get(Integer id) {
        return orderRepository.findById(id);
    }
    
    
}
