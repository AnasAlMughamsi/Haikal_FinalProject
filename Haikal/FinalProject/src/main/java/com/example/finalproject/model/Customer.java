package com.example.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

@Data
@Entity
@Table(name = "customer")
@AllArgsConstructor @NoArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "first name is required")
    private String firstName;
    @NotEmpty(message = "last name is required")
    private String lastName;

//    @NotEmpty(message = "email is required")
//    @Email(message = "Please enter a valid email")
    private String email;

//    @NotEmpty(message = "phone number is required")
    private String phoneNumber;

//    @Column(name = "dateOfBirth")
//    @DateTimeFormat(pattern = "yyyy-mm-dd")
    private String dateOfBirth;
//    @NotEmpty(message = "gender is required")
    @Pattern(regexp = "male|female")
    @Column(columnDefinition = "varchar(10) not null check (gender= 'male' or gender= 'female')")
    private String gender;


    //  Relationships

    @OneToOne
    @MapsId
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private MyUser user;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
    private List<MyOrder> myOrders;


    @ManyToMany(fetch = FetchType.LAZY,
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            },
            mappedBy = "customers")
    @JsonIgnore
//    @JoinColumn(name = "store_list")
    private List<Store> storeList;



}
