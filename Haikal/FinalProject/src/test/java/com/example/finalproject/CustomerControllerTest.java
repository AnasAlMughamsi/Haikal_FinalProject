package com.example.finalproject;

import com.example.finalproject.controller.CustomerController;
import com.example.finalproject.controller.ProductController;
import com.example.finalproject.model.Customer;
import com.example.finalproject.repository.CustomerRepository;
import com.example.finalproject.service.CustomerService;
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
@WebMvcTest(value = CustomerController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class CustomerControllerTest
{
    @MockBean
    CustomerService customerService;
    @MockBean
    CustomerRepository customerRepository;
    @Autowired
    MockMvc mockMvc;

    Customer customer1,customer2;
    List<Customer> customerList;

    @BeforeEach
    void setUp()
    {
        customer1 = new Customer(null,"adel","anas","adel@gmail.com",
                "05465656","2000-10-10","male",null,null,null);
        customer2 = new Customer(null,"adel","anas","adel@gmail.com",
                "05465656","2000-10-10","male",null,null,null);
        customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);
    }
    @Test
    public void getAllCustomersTest() throws Exception {
        Mockito.when(customerService.getAllCustomers()).thenReturn(customerList);
        mockMvc.perform(get("/api/v1/customer/all"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].firstName").value("adel"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].lastName").value("anas"));
    }
}
