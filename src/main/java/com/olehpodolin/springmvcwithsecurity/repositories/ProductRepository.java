package com.olehpodolin.springmvcwithsecurity.repositories;

import com.olehpodolin.springmvcwithsecurity.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
