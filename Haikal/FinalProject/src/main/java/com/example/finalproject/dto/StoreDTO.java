package com.example.finalproject.dto;


import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoreDTO {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String username;
    private String password;
    private String storeName;
    private String storeNameOwner;
    private String city;
    private String district;
    private String street;
    private String phoneNumber;
    private String email;
    private String companyRegisterUrl;
    private boolean isActive; // by admin
    private String commercialLicense;

}
