/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ecommerce.ecommerce.controller;

import com.ecommerce.ecommerce.model.Product;
import com.ecommerce.ecommerce.model.User;
import com.ecommerce.ecommerce.service.ProductService;
import com.ecommerce.ecommerce.service.UploadFileService;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

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

    @Autowired
    private UploadFileService upload;

    @GetMapping("")
    public String show(Model model) {
        model.addAttribute("products", productService.getAll());
        return "products/show";
    }

    @GetMapping("/create")
    public String create() {
        return "products/create";
    }

    @PostMapping("/save")
    private String save(Product product, @RequestParam("img") MultipartFile file, HttpSession session) throws IOException {
        Integer idUser = Integer.parseInt(session.getAttribute("idUser").toString());
        User user = new User(idUser, "", "", "", "", "", "", "");
        product.setUser(user);

        if (product.getId_product() != null) {
            String imageName = upload.saveImage(file);
            System.out.println("imageName: " + imageName);
            product.setImage(imageName);
        }

        productService.save(product);
        return "redirect:/products";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable int id, Model model) {
        Product product = new Product();
        Optional<Product> optionalProduct = productService.get(id);
        product = optionalProduct.get();
        model.addAttribute("product", product);
        return "products/edit";
    }

    @PostMapping("/update")
    public String update(Product product, @RequestParam("img") MultipartFile file) throws IOException  {
        
        Product oldProduct = new Product();
        oldProduct = productService.get(product.getId_product()).get();
        
        if (file.isEmpty()) { // editamos el producto pero no cambiamos la imagem

            product.setImage(oldProduct.getImage());
        } else {// cuando se edita tbn la imagen			
            //eliminar cuando no sea la imagen por defecto
            if (!oldProduct.getImage().equals("default.jpg")) {
                upload.deleteImage(oldProduct.getImage());
            }
            String nombreImagen = upload.saveImage(file);
            product.setImage(nombreImagen);
        }
        
        product.setUser(oldProduct.getUser());
        productService.update(product);
        return "redirect:/products";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable int id) {
        
        Product product = new Product();
        product = productService.get(id).get();
        if(!product.getImage().equals("default.jpg")){
            upload.deleteImage(product.getImage());
        }
        
        productService.delete(id);
        return "redirect:/products";
    }

}
