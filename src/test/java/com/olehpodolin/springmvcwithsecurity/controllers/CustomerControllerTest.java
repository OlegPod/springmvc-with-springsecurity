package com.olehpodolin.springmvcwithsecurity.controllers;

import com.olehpodolin.springmvcwithsecurity.domain.Customer;
import com.olehpodolin.springmvcwithsecurity.services.CustomerService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class CustomerControllerTest {

    @Mock
    private CustomerService customerService;

    @InjectMocks
    private CustomerController customerController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    public void listAllCustomers() throws Exception {

        List<Customer> customerList = new ArrayList<>();
        customerList.add(new Customer());
        customerList.add(new Customer());

        when(customerService.listAll()).thenReturn( (List) customerList);

        mockMvc.perform(get("/customers"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customers"))
                .andExpect(model().attribute("customers", hasSize(2)));
    }

    @Test
    public void getCustomer() throws Exception {

        Customer customer = new Customer();
        customer.setId(1L);

        when(customerService.getById(anyLong())).thenReturn(customer);

        mockMvc.perform(get("/customers/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/showcustomer"))
                .andExpect(model().attribute("customer", equalTo(customer)));
    }

    @Test
    public void editCustomer() throws Exception {

        Customer customer = new Customer();
        customer.setId(1L);

        when(customerService.getById(anyLong())).thenReturn(customer);

        mockMvc.perform(get("/customers/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerform"))
                .andExpect(model().attribute("customer", equalTo(customer)));
    }

    @Test
    public void createNewCustomer() throws Exception {

        mockMvc.perform(get("/customers/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("customer/customerform"))
                .andExpect(model().attributeExists("customer"));
    }

    @Test
    public void saveOrUpdateCustomer() throws Exception {

        Customer customer = new Customer();
        customer.setId(3L);

        when(customerService.saveOrUpdate(any())).thenReturn(customer);

        mockMvc.perform(post("/customer")
                .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("id", "")
        )
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customers/3/show"));
    }

    @Test
    public void deleteCustomer() throws Exception {

        mockMvc.perform(get("/customers/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/customers/"));

        verify(customerService, times(1)).delete(anyLong());
    }
}