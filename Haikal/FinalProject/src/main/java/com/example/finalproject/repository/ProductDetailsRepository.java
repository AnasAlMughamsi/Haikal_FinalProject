package com.example.finalproject.repository;

import com.example.finalproject.model.ProductDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDetailsRepository extends JpaRepository<ProductDetails,Integer>
{
    ProductDetails findProductDetailsById(Integer id);
}
