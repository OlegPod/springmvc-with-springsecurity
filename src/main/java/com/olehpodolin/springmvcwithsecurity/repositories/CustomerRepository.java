package com.olehpodolin.springmvcwithsecurity.repositories;

import com.olehpodolin.springmvcwithsecurity.domain.Customer;
import org.springframework.data.repository.CrudRepository;

public interface CustomerRepository extends CrudRepository<Customer, Long> {
}
