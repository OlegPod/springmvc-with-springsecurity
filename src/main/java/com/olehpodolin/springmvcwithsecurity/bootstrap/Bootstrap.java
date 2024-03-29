package com.olehpodolin.springmvcwithsecurity.bootstrap;

import com.olehpodolin.springmvcwithsecurity.domain.Address;
import com.olehpodolin.springmvcwithsecurity.domain.Customer;
import com.olehpodolin.springmvcwithsecurity.domain.Product;
import com.olehpodolin.springmvcwithsecurity.repositories.CustomerRepository;
import com.olehpodolin.springmvcwithsecurity.repositories.ProductRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class Bootstrap implements CommandLineRunner {

    private ProductRepository productRepository;

    private CustomerRepository customerRepository;

    public Bootstrap(ProductRepository productRepository, CustomerRepository customerRepository) {
        this.productRepository = productRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        loadProducts();
        loadCustomers();
    }

    private void loadProducts() {

        Product product1 = new Product();
        product1.setDescription("Product 1");
        product1.setPrice(new BigDecimal("12.99"));
        product1.setImageUrl("http://example.com/product1");

        Product product2 = new Product();
        product2.setDescription("Product 2");
        product2.setPrice(new BigDecimal("14.99"));
        product2.setImageUrl("http://example.com/product2");


        Product product3 = new Product();
        product3.setDescription("Product 3");
        product3.setPrice(new BigDecimal("34.99"));
        product3.setImageUrl("http://example.com/product3");

        Product product4 = new Product();
        product4.setDescription("Product 4");
        product4.setPrice(new BigDecimal("44.99"));
        product4.setImageUrl("http://example.com/product4");

        Product product5 = new Product();
        product5.setDescription("Product 5");
        product5.setPrice(new BigDecimal("25.99"));
        product5.setImageUrl("http://example.com/product5");

        productRepository.save(product1);
        productRepository.save(product2);
        productRepository.save(product3);
        productRepository.save(product4);
        productRepository.save(product5);
    }

    private void loadCustomers() {

        Customer customer1 = new Customer();
        customer1.setFirstName("Kirk");
        customer1.setLastName("Joo");
        customer1.setPhoneNumber("+123456789090");
        customer1.setEmail("customer@gmail.com");
        customer1.setBillingAddress(new Address());
        customer1.getBillingAddress().setAddressOne("Street One");
        customer1.getBillingAddress().setAddressTwo("Address Two");
        customer1.getBillingAddress().setCity("Lviv");
        customer1.getBillingAddress().setState("Lviv");
        customer1.getBillingAddress().setZipCode("79000");

        Customer customer2 = new Customer();
        customer2.setFirstName("Winston");
        customer2.setLastName("Andrew");
        customer2.setPhoneNumber("+123456789090");
        customer2.setEmail("customer@gmail.com");
        customer2.setBillingAddress(new Address());
        customer2.getBillingAddress().setAddressOne("Street One");
        customer2.getBillingAddress().setAddressTwo("Address Two");
        customer2.getBillingAddress().setCity("Lviv");
        customer2.getBillingAddress().setState("Lviv");
        customer2.getBillingAddress().setZipCode("79000");

        Customer customer3 = new Customer();
        customer3.setFirstName("Jonh");
        customer3.setLastName("Johnson");
        customer3.setPhoneNumber("+123456789090");
        customer3.setEmail("customer@gmail.com");
        customer3.setBillingAddress(new Address());
        customer3.getBillingAddress().setAddressOne("Street One");
        customer3.getBillingAddress().setAddressTwo("Address Two");
        customer3.getBillingAddress().setCity("Lviv");
        customer3.getBillingAddress().setState("Lviv");
        customer3.getBillingAddress().setZipCode("79000");

        Customer customer4 = new Customer();
        customer4.setFirstName("Nick");
        customer4.setLastName("Bockley");
        customer4.setPhoneNumber("+123456789090");
        customer4.setEmail("customer@gmail.com");
        customer4.setBillingAddress(new Address());
        customer4.getBillingAddress().setAddressOne("Street One");
        customer4.getBillingAddress().setAddressTwo("Address Two");
        customer4.getBillingAddress().setCity("Lviv");
        customer4.getBillingAddress().setState("Lviv");
        customer4.getBillingAddress().setZipCode("79000");

        Customer customer5 = new Customer();
        customer5.setFirstName("Leyla");
        customer5.setLastName("Jinde");
        customer5.setPhoneNumber("+123456789090");
        customer5.setEmail("customer@gmail.com");
        customer5.setBillingAddress(new Address());
        customer5.getBillingAddress().setAddressOne("Street One");
        customer5.getBillingAddress().setAddressTwo("Address Two");
        customer5.getBillingAddress().setCity("Lviv");
        customer5.getBillingAddress().setState("Lviv");
        customer5.getBillingAddress().setZipCode("79000");

        customerRepository.save(customer1);
        customerRepository.save(customer2);
        customerRepository.save(customer3);
        customerRepository.save(customer4);
        customerRepository.save(customer5);
    }
}
