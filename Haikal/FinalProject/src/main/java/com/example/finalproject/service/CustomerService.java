package com.example.finalproject.service;

import com.example.finalproject.api.ApiException;
import com.example.finalproject.dto.CustomerDTO;
import com.example.finalproject.model.*;
import com.example.finalproject.repository.CustomerRepository;
import com.example.finalproject.repository.MyOrderRepository;
import com.example.finalproject.repository.MyUserRepository;
import com.example.finalproject.repository.StoreRepository;
import jakarta.persistence.criteria.Order;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final MyUserRepository myUserRepository;
    private final StoreRepository storeRepository;
    private final MyOrderRepository myOrderRepository;

    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public Customer getCustomerById(Integer id) {
        Customer customer = customerRepository.findCustomerById(id);
        if(customer == null) {
            throw new ApiException("Customer not found");
        }
        return customer;
    }

    public void addCustomer(Customer customer) {
        customerRepository.save(customer);
    }

    public void updateCustomer(Customer updateCustomer, Integer user_id) {
        MyUser myUser = myUserRepository.findMyUserById(user_id);
        Customer customer = customerRepository.findCustomerById(myUser.getCustomer().getId());
        if(customer == null) {
            throw new ApiException("Customer not found, wrong id");
        } else if (customer.getUser().getId() != user_id) {
            throw new ApiException("Sorry, you have no right access");
        }
        updateCustomer.setId(user_id);
        updateCustomer.setUser(myUser);
        customerRepository.save(updateCustomer);
    }

    public void deleteCustomer(Integer id) {
        Customer customer = customerRepository.findCustomerById(id);
        if(customer == null) {
            throw new ApiException("Wrong customer id");
        }
        customerRepository.delete(customer);
    }

    public void assignCustomerToStore(Integer store_id, Integer customer_id, Integer auth_id) {
        Customer customer = customerRepository.findCustomerById(customer_id);
        Store store = storeRepository.findStoreById(store_id);
        if(store == null) {
            throw new ApiException("store not found");
        }
        if(customer == null) {
            throw new ApiException("customer not found");
        }

        store.getCustomers().add(customer);
        customer.getStoreList().add(store);
        customerRepository.save(customer);
        storeRepository.save(store);
    }

    public String getCustomerByOrderId(Integer customer_id, Integer order_id, Integer auth_id) {
        Customer customer = customerRepository.findCustomerById(customer_id);
        MyOrder myOrder = myOrderRepository.findMyOrderById(order_id);
        if(customer == null) {
            throw new ApiException("store not found");
        }
        if(myOrder == null) {
            throw new ApiException("customer not found");
        }

        if (myOrder.getCustomer().getId() == customer_id) {
            return "customer: " + customer;
        }
        return "Customer has no order";
    }


    public List<MyOrder> getOrderByCustomerId(Integer customer_id, Integer auth_id) {
        Customer customer = customerRepository.findCustomerById(customer_id);
        if(customer == null) {
            throw new ApiException("store not found");
        }

        return customer.getMyOrders();
    }

}
