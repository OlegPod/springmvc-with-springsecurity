package com.olehpodolin.springmvcwithsecurity.controllers;

import com.olehpodolin.springmvcwithsecurity.domain.Product;
import com.olehpodolin.springmvcwithsecurity.services.ProductService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public String listAllProducts(Model model) {

        model.addAttribute("products", productService.listAll());

        return "product/products";
    }

    @GetMapping("/products/{id}/show")
    public String getProduct(Model model, @PathVariable Long id) {

        model.addAttribute("product", productService.getById(id));

        return "product/showproduct";
    }

    @GetMapping("/products/{id}/edit")
    public String editProduct(@PathVariable Long id, Model model) {

        model.addAttribute("product", productService.getById(id));

        return "product/productform";
    }

    @GetMapping("/products/new")
    public String createNewProduct(Model model) {

        model.addAttribute("product", new Product());

        return "product/productform";
    }

    @PostMapping("/product")
    public String saveOrUpdateProduct(Product product) {

        Product savedProduct = productService.saveOrUpdate(product);

        return "redirect:/products/" + savedProduct.getId() + "/show";
    }

    @GetMapping("/products/{id}/delete")
    public String deleteProduct(@PathVariable Long id) {

        productService.delete(id);

        return "redirect:/products/";
    }

}
