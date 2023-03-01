package com.example.finalproject.service;

import com.example.finalproject.api.ApiException;
import com.example.finalproject.model.*;
import com.example.finalproject.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyOrderService
{
    private final MyOrderRepository myOrderRepository;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final StoreRepository storeRepository;
    private final MyUserRepository myUserRepository;

    public List<MyOrder> getAllOrder() {
        return myOrderRepository.findAll();
    }

    public MyOrder getOrderById(Integer id) {
        MyOrder myOrder = myOrderRepository.findMyOrderById(id);
        if(myOrder == null) {
            throw new ApiException("Order not found");
        }
        return myOrder;
    }
    public void addOrder(MyOrder order) {
        myOrderRepository.save(order);
    }

    public boolean updateOrder(Integer id,MyOrder order) {
        MyOrder order1 = myOrderRepository.findMyOrderById(id);
        if(order1==null)
            return false;
        order.setId(id);
        myOrderRepository.save(order);
        return true;
    }
    public boolean deleteOrder(Integer id) {
        MyOrder order = myOrderRepository.findMyOrderById(id);
        if(order==null)
            return false;
        myOrderRepository.delete(order);
        return true;
    }


    public void assignOrderToCustomer(Integer customer_id, Integer order_id, Integer auth_id) {
        Customer customer = customerRepository.findCustomerById(customer_id);
        MyOrder myOrder = myOrderRepository.findMyOrderById(order_id);

        if(customer == null || myOrder == null)  {
            throw new ApiException("Customer or order not found");
        } else if (customer.getUser().getId() != auth_id) {
            throw new ApiException("Unauthorized");
        }

        myOrder.setCustomer(customer);
        customer.getMyOrders().add(myOrder);
        myOrderRepository.save(myOrder);
        customerRepository.save(customer);
    }

    public void assignOrderToProduct(Integer product_id, Integer order_id, Integer auth_id) {
        Product product = productRepository.findProductById(product_id);
        MyOrder myOrder = myOrderRepository.findMyOrderById(order_id);

        if(product == null || myOrder == null)  {
            throw new ApiException("Customer or order not found");
        }
        product.getMyOrderList().add(myOrder);
        myOrder.getProductList().add(product);
        productRepository.save(product);
        myOrderRepository.save(myOrder);
    }
    public void assignOrderToStore(Integer store_id, Integer order_id, Integer auth_id) {
        Store store = storeRepository.findStoreById(store_id);
        MyOrder myOrder = myOrderRepository.findMyOrderById(order_id);

        if(store == null || myOrder == null)  {
            throw new ApiException("Customer or order not found");
        }
        store.getOrderList().add(myOrder);
        myOrder.setStore_orders(store);
        storeRepository.save(store);
        myOrderRepository.save(myOrder);
    }


    public List<MyOrder> getOrdersByStoreId(Integer auth_id, Integer store_id) {
//        MyOrder myOrder = myOrderRepository.findMyOrderById(order_id);
        Store store = storeRepository.findStoreById(store_id);
        if(store == null) {
            throw new ApiException("Store not found");
        }
//        for (int i = 0; i < myOrder.getProductList().size(); i++) {
//            if (myOrder.getProductList().get(i).getStore_owner().getId() == store.getId()) {
//                return "Store orders: " + myOrder;
//            }
//        }
        return store.getOrderList();

    }



}
