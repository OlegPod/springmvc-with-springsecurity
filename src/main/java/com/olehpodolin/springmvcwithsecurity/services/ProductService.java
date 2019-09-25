package com.olehpodolin.springmvcwithsecurity.services;

import com.olehpodolin.springmvcwithsecurity.domain.Product;

import java.util.List;

public interface ProductService {

    List<Product> listAllProducts();

    Product save(Product product);
}
