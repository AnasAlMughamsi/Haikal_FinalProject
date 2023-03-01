package com.example.finalproject;

import com.example.finalproject.model.MyOrder;
import com.example.finalproject.model.Product;
import com.example.finalproject.repository.ProductRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTesting
{
    @Autowired
    ProductRepository productRepository;

    Product product;

    @BeforeEach
    void setUp()
    {
        product = new Product(null,"door",15,125,"camry","toyota",2020,
                "green",true,"GLX","Good","kjksjdksfhs",null,null,null);
    }

    @Test
    public void findProductDetailsByIdTest()
    {
        productRepository.save(product);
        Product product1 = productRepository.findProductById(product.getId());
        Assertions.assertThat(product1).isEqualTo(product);
    }
}
