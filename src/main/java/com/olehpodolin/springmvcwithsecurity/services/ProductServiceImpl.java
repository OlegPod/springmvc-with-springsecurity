package com.olehpodolin.springmvcwithsecurity.services;

import com.olehpodolin.springmvcwithsecurity.domain.Product;
import com.olehpodolin.springmvcwithsecurity.repositories.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<Product> listAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Override
    public Product getProductById(Long id) {

        if (productRepository.existsById(id)) {
            return productRepository.findById(id).get();
        } else {
            throw new RuntimeException("Product id incorrect");
        } //todo implement better handling
    }
}
