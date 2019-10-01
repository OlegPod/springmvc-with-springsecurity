package com.olehpodolin.springmvcwithsecurity.controllers;

import com.olehpodolin.springmvcwithsecurity.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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

    @GetMapping("/products/{id}/show")
    public String getProduct(Model model, @PathVariable String id) {

        model.addAttribute("product", productService.getProductById(Long.valueOf(id)));

        return "showproduct";
    }

}
