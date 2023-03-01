package com.example.finalproject;

import com.example.finalproject.controller.MyOrderController;
import com.example.finalproject.model.MyOrder;
import com.example.finalproject.repository.MyOrderRepository;
import com.example.finalproject.service.MyOrderService;
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
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@WebMvcTest(value = MyOrderController.class, excludeAutoConfiguration = {SecurityAutoConfiguration.class})
public class MyOrderControllerTesting {
    @MockBean
    MyOrderService myOrderService;
    @MockBean
    MyOrderRepository myOrderRepository;

    @Autowired
    MockMvc mockMvc;

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
    public void getAllOrderTest() throws Exception {
        Mockito.when(myOrderService.getAllOrder()).thenReturn(myOrderList);
        mockMvc.perform(get("/api/v1/order/all"))
                .andExpect(status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$", Matchers.hasSize(2)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].totalPrice").value(125))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].status").value("Good"));
    }
}
