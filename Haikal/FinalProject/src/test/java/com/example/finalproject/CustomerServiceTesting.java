package com.example.finalproject;

import com.example.finalproject.model.Customer;
import com.example.finalproject.repository.CustomerRepository;
import com.example.finalproject.service.CustomerService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTesting
{
    @InjectMocks
    CustomerService customerService;
    @Mock
    CustomerRepository customerRepository;

    Customer customer1,customer2;
    List<Customer> customerList;

    @BeforeEach
    void setUp()
    {
        customer1 = new Customer(null,"adel","anas","adel@gmail.com","045645168",
                "2000-10-10","male",null,null,null);
        customer2 = new Customer(null,"adel","anas","adel@gmail.com","045645168",
                "2000-10-10","male",null,null,null);
        customerList = new ArrayList<>();
        customerList.add(customer1);
        customerList.add(customer2);
    }
    @Test
    public void getAllCustomersTest()
    {
        when(customerRepository.findAll()).thenReturn(customerList);
        List<Customer> list= customerRepository.findAll();
        Assertions.assertEquals(2,list.size());
        verify(customerRepository,times(1)).findAll();
    }
    @Test
    public void addCustomerTest()
    {
        customerService.addCustomer(customer1);
        verify(customerRepository, times(1)).save(customer1);
    }

    @Test
    public void deleteCustomerTest()
    {
        when(customerRepository.findCustomerById(customer1.getId())).thenReturn(customer1);
        customerService.deleteCustomer(customer1.getId());
        verify(customerRepository, times(1)).findCustomerById(customer1.getId());
        verify(customerRepository, times(1)).delete(customer1);
    }

}
