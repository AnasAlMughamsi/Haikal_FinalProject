package com.example.finalproject.controller;

import com.example.finalproject.api.ApiResponse;
import com.example.finalproject.model.Customer;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.Store;
import com.example.finalproject.service.CustomerService;
import com.example.finalproject.service.MyUserDetailsService;
import com.example.finalproject.service.MyUserService;
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
@RequestMapping("/api/v1/admin")
public class AdminController {

    // this login controller for admin
    private final MyUserService myUserService;
    private final CustomerService customerService;
    private final StoreService storeService;



    @GetMapping("/all-users")
    public ResponseEntity<List<MyUser>> getAllUsers() {
        List<MyUser> userList = myUserService.getAllMyUsers();
        return ResponseEntity.status(200).body(userList);
    }
    @GetMapping("/user/{id}")
    public ResponseEntity getUserById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(myUserService.getMyUserById(id));
    }

    @GetMapping("/all-customers")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = myUserService.getAllCustomers();
        return ResponseEntity.status(200).body(customers);
    }

    @GetMapping("/customer/{id}")
    public ResponseEntity getCustomerById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(customerService.getCustomerById(id));
    }

    @GetMapping("/all-store")
    public ResponseEntity<List<Store>> getAllStores() {
        List<Store> store = storeService.getAllStores();
        return ResponseEntity.status(200).body(store);
    }

    @GetMapping("/store/{id}")
    public ResponseEntity getStoreById(@PathVariable Integer id) {
        return ResponseEntity.status(200).body(storeService.getStoreById(id));
    }


    @PostMapping("/store-status/{store_id}")
    public ResponseEntity changeStoreStatus(@PathVariable Integer store_id) {
        storeService.changeStoreStatus(store_id);
        return ResponseEntity.status(200).body("change store status");
    }

// =========================== update/delete ===========================
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateUser(@PathVariable Integer id, @Valid @RequestBody MyUser updateUser,
                                             @AuthenticationPrincipal MyUser myUser) {
        myUserService.updateMyUser(updateUser, id, myUser.getId());
        return ResponseEntity.status(200).body("User updated!");
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Integer id) {
        myUserService.deleteMyUser(id);
        return ResponseEntity.status(200).body("User deleted!");
    }
}
