package com.olehpodolin.springmvcwithsecurity.controllers;

import com.olehpodolin.springmvcwithsecurity.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @RequestMapping("/products")
    public String listAllProducts(Model model) {

        model.addAttribute("products", productService.listAllProducts());

        return "products";
    }
}
