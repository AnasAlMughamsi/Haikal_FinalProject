package com.example.finalproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
@Table(name = "orders")
public class MyOrder {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull(message = "Total price can not be null")
    @Positive( message = "Total price must be positive!")
    private double totalPrice;
    @NotEmpty(message = "The status can not be empty")
    private String status;
    @Column(columnDefinition = "Timestamp not null")
    private Date dateReceived;

    @ManyToMany
    @JsonIgnore
//    @JoinColumn(name = "myOrderList", referencedColumnName = "id")
    private List<Product> productList;

    @ManyToOne
    @JsonIgnore
    private Customer customer;

    @ManyToOne
    @JsonIgnore
    private Store store_orders;

}
