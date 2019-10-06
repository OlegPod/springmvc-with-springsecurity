package com.olehpodolin.springmvcwithsecurity.repositories;

import com.olehpodolin.springmvcwithsecurity.domain.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Long> {
}
