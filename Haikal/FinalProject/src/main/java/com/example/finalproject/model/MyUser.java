package com.example.finalproject.model;


import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Null;
import jakarta.validation.constraints.Pattern;
import lombok.*;
import org.hibernate.Hibernate;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.*;

@Data
@Entity
@AllArgsConstructor @NoArgsConstructor
//@Table(name = "users")
public class MyUser implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
//    @NotEmpty(message = "username is required")
    private String username;

//    @NotEmpty(message = "password is required")
    private String password;

    @Pattern(regexp = "(customer|store|admin)" , message = "role must be customer or admin")
    @Column(columnDefinition = "varchar(10) not null check (role = 'customer' or role = 'store' or role = 'admin')")
    private String role;


    @OneToOne(fetch = FetchType.LAZY ,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Customer customer;

    @OneToOne(fetch = FetchType.LAZY ,mappedBy = "user")
    @PrimaryKeyJoinColumn
    private Store store;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singleton(new SimpleGrantedAuthority(this.role));
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
