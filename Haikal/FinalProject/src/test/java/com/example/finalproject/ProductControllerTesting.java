package com.example.finalproject;

import com.example.finalproject.controller.MyOrderController;
import com.example.finalproject.controller.ProductController;
import com.example.finalproject.model.Product;
import com.example.finalproject.repository.ProductRepository;
import com.example.finalproject.service.ProductService;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = ProductController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class ProductControllerTesting
{
    @MockBean
    ProductService productService;
    @MockBean
    ProductRepository productRepository;

    @Autowired
    MockMvc mockMvc;

    Product product1,product2;

    List<Product> productList;

    @BeforeEach
    void setUp()
    {
        product1 = new Product(null,"door",15,125,"camry",
                "toyota",2020,"green",true,"GLX",
                "Good","dkhsfjhsfjsf",null,null,null);
        product2 = new Product(null,"door",15,125,"camry",
                "toyota",2020,"green",true,"GLX",
                "Good","dkhsfjhsfjsf",null,null,null);
        productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
    }
    @Test
    public void getProductByIdTest() throws Exception {
        Mockito.when(productService.getAllProduct()).thenReturn(productList);
        mockMvc.perform(get("/api/v1/product/all"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].productName").value("door"));
    }
}
