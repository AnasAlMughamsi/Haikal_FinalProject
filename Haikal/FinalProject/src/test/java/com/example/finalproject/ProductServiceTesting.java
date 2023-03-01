package com.example.finalproject;

import com.example.finalproject.model.MyOrder;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.Product;
import com.example.finalproject.repository.MyUserRepository;
import com.example.finalproject.repository.ProductRepository;
import com.example.finalproject.service.MyUserService;
import com.example.finalproject.service.ProductService;
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
public class ProductServiceTesting
{
    @InjectMocks
    ProductService productService;
    @Mock
    ProductRepository productRepository;
    Product product1,product2;
    List<Product> productList;

    @BeforeEach
    void setUp()
    {
        product1 = new Product(null,"door",12,125,"camry",
                "toyota",2020,"green",true,
                "GLX","Good","asdfghjkl",null,null,null);
        product2 = new Product(null,"door",12,125,"camry",
                "toyota",2020,"green",true,
                "GLX","Good","asdfghjkl",null,null,null);
        productList = new ArrayList<>();
        productList.add(product1);
        productList.add(product2);
    }
    @Test
    public void getAllProductTest()
    {
        when(productRepository.findAll()).thenReturn(productList);
        List<Product> list= productRepository.findAll();
        Assertions.assertEquals(2,list.size());
        verify(productRepository,times(1)).findAll();
    }
    @Test
    public void addProductTest()
    {
        productService.addProduct(product1);
        verify(productRepository, times(1)).save(product1);
    }

    @Test
    public void updateProductTest()
    {
        when(productRepository.findProductById(product1.getId())).thenReturn(product1);
        productService.updateProduct(product1.getId(),product1);
        verify(productRepository, times(1)).findProductById(product1.getId());
        verify(productRepository, times(1)).save(product1);
    }
    @Test
    public void deleteProductTest()
    {
        when(productRepository.findProductById(product1.getId())).thenReturn(product1);
        productService.deleteProduct(product1.getId());
        verify(productRepository, times(1)).findProductById(product1.getId());
        verify(productRepository, times(1)).delete(product1);
    }
}
