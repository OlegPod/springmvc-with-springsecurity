package com.olehpodolin.springmvcwithsecurity.controllers;

import com.olehpodolin.springmvcwithsecurity.domain.Product;
import com.olehpodolin.springmvcwithsecurity.services.ProductService;
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

public class ProductControllerTest {

    @Mock
    private ProductService productService;

    @InjectMocks
    private ProductController productController;

    private MockMvc mockMvc;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);

        mockMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }

    @Test
    public void listAllProducts() throws Exception {

        List<Product> productList = new ArrayList<>();
        productList.add(new Product());
        productList.add(new Product());

        when(productService.listAll()).thenReturn((List) productList);

        mockMvc.perform(get("/products"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/products"))
                .andExpect(model().attribute("products", hasSize(2)));
    }

    @Test
    public void getProduct() throws Exception {

        Product product = new Product();
        product.setId(1L);

        when(productService.getById(any())).thenReturn(product);

        mockMvc.perform(get("/products/1/show"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/showproduct"))
                .andExpect(model().attribute("product", equalTo(product)));
    }

    @Test
    public void editProduct() throws Exception {

        Product product = new Product();
        product.setId(1L);

        when(productService.getById(anyLong())).thenReturn(product);

        mockMvc.perform(get("/products/1/edit"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productform"))
                .andExpect(model().attribute("product", equalTo(product)));
    }

    @Test
    public void createNewProduct() throws Exception {

        mockMvc.perform(get("/products/new"))
                .andExpect(status().isOk())
                .andExpect(view().name("product/productform"))
                .andExpect(model().attributeExists("product"));
    }

    @Test
    public void saveOrUpdateProduct() throws Exception {

        Product product = new Product();
        product.setId(2L);

        when(productService.saveOrUpdate(any())).thenReturn(product);

        mockMvc.perform(post("/product")
                        .contentType(MediaType.APPLICATION_FORM_URLENCODED)
                        .param("id", "")
        )
                        .andExpect(status().is3xxRedirection())
                        .andExpect(view().name("redirect:/products/2/show"));
    }

    @Test
    public void deleteProduct() throws Exception {
        mockMvc.perform(get("/products/1/delete"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/products/"));

        verify(productService, times(1)).delete(anyLong());
    }
}