package com.example.finalproject.controller;

import com.example.finalproject.api.ApiResponse;
import com.example.finalproject.dto.CustomerDTO;
import com.example.finalproject.dto.StoreDTO;
import com.example.finalproject.model.Customer;
import com.example.finalproject.model.MyOrder;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.Store;
import com.example.finalproject.service.CustomerService;
import com.example.finalproject.service.MyOrderService;
import com.example.finalproject.service.StoreService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/store")
public class StoreController {

    private final StoreService storeService;
    private final CustomerService customerService;
    private final MyOrderService myOrderService;

    @GetMapping("/all")
    public ResponseEntity<List<Store>> getAllStore() {
        List<Store> stores = storeService.getAllStores();
        return ResponseEntity.status(200).body(stores);
    }
    @GetMapping("/get/{id}")
    public ResponseEntity<Store> getStoreById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(storeService.getStoreById(id));
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStore(@Valid @RequestBody Store store) {
        storeService.addStore(store);
        return ResponseEntity.status(200).body("Store added!");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateStore(@PathVariable Integer id, @Valid @RequestBody Store updateStore) {
        storeService.updateStore(updateStore, id);
        return ResponseEntity.status(200).body("Store updated!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteStore(@PathVariable Integer id) {
        storeService.deleteStore(id );
        return ResponseEntity.status(200).body("Store deleted!");
    }




    // ==================== assigns ====================



}
