/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

/**
 *
 * @author Oghma
 */
@Configuration
@EnableWebSecurity
public class SpringBootSecurity {
    
    private UserDetailService userDetailService;
    
    @Autowired
    private PasswordEncoder passwordEncoder;
    
    @Autowired
    private AuthenticationFailureHandler customAuthenticationFailureHandler;
    
    public SpringBootSecurity(UserDetailService userDetailService, PasswordEncoder passwordEncoder) {
        this.userDetailService = userDetailService;
        this.passwordEncoder = passwordEncoder;
    }
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/", "/css/**", "/vendor/**", "/images/**", "/producthome/{id}", "/user/register", "user/save", "/user/access", "/user/logout", "/search").permitAll()
                .requestMatchers("/admin/**").hasRole("admin")
                .requestMatchers("/products/**").hasRole("admin")
                .requestMatchers("/user/**", "/cart", "/delete/cart/{idProduct}", "/getCart", "/order", "/saveOrder").hasRole("user")
                )
                .formLogin((form) -> form
                .loginPage("/user/login")
                .defaultSuccessUrl("/user/access", true)
                .failureUrl("/user/login?error=true").failureHandler(customAuthenticationFailureHandler)
                .permitAll()
                )
                .logout((logout) -> logout.permitAll());
        
        return http.build();
    }
    
}
