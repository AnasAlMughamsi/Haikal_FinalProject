package com.example.finalproject;

import com.example.finalproject.model.MyOrder;
import com.example.finalproject.repository.MyOrderRepository;
import com.example.finalproject.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Date;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MyOrderRepositoryTesting
{
    @Autowired
    MyOrderRepository myOrderRepository;

    MyOrder order1;

    @BeforeEach
    void setUp() {
        order1 = new MyOrder(null,125,"Good",new Date(),null,null,null);
        myOrderRepository.save(order1);
    }

    @Test
    public void findMyOrderByIdTest() {
        myOrderRepository.save(order1);
        MyOrder orders = myOrderRepository.findMyOrderById(order1.getId());
        Assertions.assertThat(orders).isEqualTo(order1);
    }
}
