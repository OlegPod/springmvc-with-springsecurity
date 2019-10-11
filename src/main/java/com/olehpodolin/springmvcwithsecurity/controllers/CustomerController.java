package com.olehpodolin.springmvcwithsecurity.controllers;

import com.olehpodolin.springmvcwithsecurity.domain.Customer;
import com.olehpodolin.springmvcwithsecurity.services.CustomerService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class CustomerController {

    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public String listAllCustomers(Model model) {

        model.addAttribute("customers", customerService.listAll());

        return "customer/customers";
    }

    @GetMapping("/customers/{id}/show")
    public String getCustomer(@PathVariable Long id, Model model) {

        model.addAttribute("customer", customerService.getById(id));

        return "customer/showcustomer";
    }

    @GetMapping("/customers/{id}/edit")
    public String editCustomer(@PathVariable Long id, Model model) {

        model.addAttribute("customer", customerService.getById(id));

        return "customer/customerform";
    }

    @GetMapping("/customers/new")
    public String createNewCustomer(Model model) {

        model.addAttribute("customer", new Customer());

        return "customer/customerform";
    }

    @PostMapping("/customer")
    public String saveOrUpdateCustomer(Customer customer) {

        Customer savedCustomer = customerService.saveOrUpdate(customer);

        return "redirect:/customers/" + savedCustomer.getId() + "/show";
    }

    @GetMapping("/customers/{id}/delete")
    public String deleteCustomer(@PathVariable Long id) {

        customerService.delete(id);

        return "redirect:/customers/";
    }
}
