package com.example.finalproject.controller;

import com.example.finalproject.dto.ProductDetailsDTO;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.Product;
import com.example.finalproject.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/product")
public class ProductController
{
    private final ProductService productService;

    @GetMapping("/get/{id}")
    public ResponseEntity getProductById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(productService.getProductById(id));
    }

    @GetMapping("/all")
    public ResponseEntity getAllProduct() {
        List<Product>productList = productService.getAllProduct();
        return ResponseEntity.status(200).body(productList);
    }
    @PostMapping("/add")
    public ResponseEntity addProduct(@Valid @RequestBody Product product) {
        productService.addProduct(product);
        return ResponseEntity.status(200).body("Product added !");
    }
//    @PostMapping("/add-details")
//    public ResponseEntity<String> addProductDetails(@Valid @RequestBody ProductDetailsDTO productDTO) {
//        productService.addProductDetails(productDTO);
//        return ResponseEntity.status(200).body("Product details added!!!");
//    }


    @PutMapping("/update/{id}")
    public ResponseEntity updateProduct(@PathVariable Integer id,@Valid @RequestBody Product product) {
        boolean isAvailable = productService.updateProduct(id, product);
        if(isAvailable)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong Id !");
        return ResponseEntity.status(HttpStatus.OK).body("Product updated !");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteProduct(@PathVariable Integer id) {
        boolean isAvailable = productService.deleteProduct(id);
        if(isAvailable)
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong Id !");
        return ResponseEntity.status(HttpStatus.OK).body("Product deleted !");
    }
    @PostMapping("/add-product/{store_id}/{product_id}")
    public ResponseEntity<String> deleteProduct(@PathVariable Integer store_id, @PathVariable Integer product_id,
                                                @AuthenticationPrincipal MyUser myUser) {
        productService.assignProductToStore(store_id, product_id, myUser.getId());
        return ResponseEntity.status(HttpStatus.OK).body("Assign product to store");
    }
}
