package com.example.finalproject;

import com.example.finalproject.model.MyOrder;
import com.example.finalproject.repository.MyOrderRepository;
import com.example.finalproject.service.MyOrderService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class MyOrderServiceTesting
{
    @InjectMocks
    MyOrderService myOrderService;
    @Mock
    MyOrderRepository myOrderRepository;

    MyOrder myOrder1,myOrder2;
    List<MyOrder> myOrderList;

    @BeforeEach
    void setUp() {
        myOrder1 = new MyOrder(null,125,"Good",new Date(),null,null,null);
        myOrder2 = new MyOrder(null,125,"Good",new Date(),null,null,null);
        myOrderList = new ArrayList<>();
        myOrderList.add(myOrder1);
        myOrderList.add(myOrder2);
    }
    @Test
    public void getAllOrderTest() {
        when(myOrderRepository.findAll()).thenReturn(myOrderList);
        List<MyOrder> list= myOrderRepository.findAll();
        Assertions.assertEquals(2,list.size());
        verify(myOrderRepository,times(1)).findAll();
    }
    @Test
    public void addOrderTest() {
        myOrderService.addOrder(myOrder1);
        verify(myOrderRepository, times(1)).save(myOrder1);
    }

    @Test
    public void updateOrderTest() {
        when(myOrderRepository.findMyOrderById(myOrder1.getId())).thenReturn(myOrder1);
        myOrderService.updateOrder(myOrder1.getId(),myOrder1);
        verify(myOrderRepository, times(1)).findMyOrderById(myOrder1.getId());
        verify(myOrderRepository, times(1)).save(myOrder1);
    }
    @Test
    public void deleteOrderTest()
    {
        when(myOrderRepository.findMyOrderById(myOrder1.getId())).thenReturn(myOrder1);
        myOrderService.deleteOrder(myOrder1.getId());
        verify(myOrderRepository, times(1)).findMyOrderById(myOrder1.getId());
        verify(myOrderRepository, times(1)).delete(myOrder1);
    }
}
