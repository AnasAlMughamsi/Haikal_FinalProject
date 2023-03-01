package com.example.finalproject.service;

import com.example.finalproject.api.ApiException;
import com.example.finalproject.dto.ProductDetailsDTO;
import com.example.finalproject.model.Product;
import com.example.finalproject.model.ProductDetails;
import com.example.finalproject.model.Store;
import com.example.finalproject.repository.ProductDetailsRepository;
import com.example.finalproject.repository.ProductRepository;
import com.example.finalproject.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService
{
    private final ProductRepository productRepository;
    private final ProductDetailsRepository productDetailsRepository;
    private final StoreRepository storeRepository;


    public List<Product> getAllProduct() {
        return productRepository.findAll();
    }

    public Product getProductById(Integer id) {
        Product product = productRepository.findProductById(id);
        if(product == null) {
            throw new ApiException("product not found!");
        }
        return product;
    }
    public void addProduct(Product product) {
        productRepository.save(product);
    }
    public void addProductDetails(ProductDetailsDTO productDTO) {
        Product product = productRepository.findProductById(productDTO.getProduct_id());
        if(product == null) {
            throw new ApiException("product not found!");
        }

        ProductDetails productDetails =new ProductDetails(null, productDTO.getQuantity(), product);
        productDetailsRepository.save(productDetails);
    }

    public void updateProductDetails(ProductDetailsDTO updateProductDTO) {
        ProductDetails productDetails = productDetailsRepository.findProductDetailsById(updateProductDTO.getProduct_id());
        if(productDetails == null) {
            throw new ApiException("product not found!");
        }
//        updateProductDTO.setProduct_id(productDetails.getId());
        productDetails.setQuantity(updateProductDTO.getQuantity());
        productDetailsRepository.save(productDetails);
    }

    public void deleteProductDetails(Integer id) {
        ProductDetails productDetails = productDetailsRepository.findProductDetailsById(id);
        if(productDetails == null) {
            throw new ApiException("product details not found!");
        }
        productDetailsRepository.delete(productDetails);
    }

    public boolean updateProduct(Integer id, Product product) {
        Product product1 = productRepository.findProductById(id);
        if(product1==null) {
            throw new ApiException("product not found!");
        }
        product.setId(id);
        productRepository.save(product);
        return true;
    }
    public boolean deleteProduct(Integer id) {
        Product product = productRepository.findProductById(id);
        if(product==null) {
            throw new ApiException("product not found!");
        }
        productRepository.delete(product);
        return true;
    }

    public void assignProductToStore(Integer store_id, Integer product_id, Integer auth_id) {
        Store store = storeRepository.findStoreById(store_id);
        Product product = productRepository.findProductById(product_id);
        if(store == null || product == null) {
            throw new ApiException("Store or product not found!");
        } else if(store.getUser().getId() != auth_id) {
            throw new ApiException("you have no right to assign product!");
        }
        product.setStore_owner(store);
        store.getProductList().add(product);
        storeRepository.save(store);
        productRepository.save(product);
    }
}
