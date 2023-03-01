package com.example.finalproject.repository;

import com.example.finalproject.model.Customer;
import com.example.finalproject.model.MyOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MyOrderRepository extends JpaRepository<MyOrder, Integer> {

    MyOrder findMyOrderById(Integer id);
//    MyOrder findMyOrderByCustomer_order(Customer customer);

}
