package com.example.finalproject.controller;


import com.example.finalproject.api.ApiResponse;
import com.example.finalproject.dto.CustomerDTO;
import com.example.finalproject.dto.StoreDTO;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.service.MyUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/user")
public class UserController {

    private final MyUserService myUserService;


    @PostMapping("/login")
    public ResponseEntity customerLogin(@AuthenticationPrincipal MyUser myUser) {
        if(myUser.getRole().equals("customer")) {
            return ResponseEntity.status(HttpStatus.OK).body("customer logged in successfully");
        } else if (myUser.getRole().equals("store")) {
            return ResponseEntity.status(HttpStatus.OK).body("store logged in successfully");
        } else if (myUser.getRole().equals("admin")) {
            return ResponseEntity.status(HttpStatus.OK).body("admin logged in successfully");
        } else {
            return ResponseEntity.status(HttpStatus.OK).body("Welcome");
        }

    }

    @PostMapping("/logout")
    public ResponseEntity<ApiResponse> logout() {
        return ResponseEntity.status(HttpStatus.OK).body(new ApiResponse("Logout successfully", 201));
    }

    @PostMapping("/register/customer")
    public ResponseEntity registerCustomer(@RequestBody @Valid CustomerDTO customerDTO) {
        myUserService.customerRegister(customerDTO);
        return ResponseEntity.status(HttpStatus.OK).body("customer register");
    }

    @PostMapping("/register/store")
    public ResponseEntity registerStore(@RequestBody @Valid StoreDTO storeDTO) {
        myUserService.registerStore(storeDTO);
        return ResponseEntity.status(HttpStatus.OK).body("store register");
    }

}