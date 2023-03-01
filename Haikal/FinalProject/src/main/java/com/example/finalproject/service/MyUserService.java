package com.example.finalproject.service;

import com.example.finalproject.api.ApiException;
import com.example.finalproject.dto.CustomerDTO;
import com.example.finalproject.dto.StoreDTO;
import com.example.finalproject.model.Customer;
import com.example.finalproject.model.Store;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.repository.CustomerRepository;
import com.example.finalproject.repository.StoreRepository;
import com.example.finalproject.repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MyUserService {

    private final MyUserRepository userRepository;
    private final CustomerRepository customerRepository;
    private final StoreRepository storeRepository;



    // TODO: need to check if I need these CRUD for MyUser !
    public List<MyUser> getAllMyUsers() {
        return userRepository.findAll();
    }
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    public MyUser getMyUserById(Integer auth_id) {
        MyUser user = userRepository.findMyUserById(auth_id);
        if(user == null) {
            throw new ApiException("user not found");
        }
        return user;
    }


    public void updateMyUser(MyUser updateMyUser, Integer id) {
        MyUser user = userRepository.findMyUserById(id);
        if(user == null) {
            throw new ApiException("user not found, wrong id");
        }
        updateMyUser.setId(id);
        userRepository.save(updateMyUser);
    }

    public void deleteMyUser(Integer id) {
        MyUser user = userRepository.findMyUserById(id);
        if(user == null) {
            throw new ApiException("Wrong user id");
        }
        userRepository.delete(user);
    }



    public void customerRegister(CustomerDTO customerDTO) {

        MyUser myUser = new MyUser();
        myUser.setUsername(customerDTO.getUsername());
        String hashedPassword = new BCryptPasswordEncoder().encode(customerDTO.getPassword());
        myUser.setPassword(hashedPassword);
        myUser.setRole("customer");

        MyUser savedUser = userRepository.save(myUser);

        Customer newCustomer = new Customer();
        newCustomer.setFirstName(customerDTO.getFirstName());
        newCustomer.setLastName(customerDTO.getLastName());
        newCustomer.setEmail(customerDTO.getEmail());
        newCustomer.setPhoneNumber(customerDTO.getPhoneNumber());
        newCustomer.setDateOfBirth(customerDTO.getDateOfBirth());
        newCustomer.setGender(customerDTO.getGender());
        newCustomer.setUser(savedUser);
//        customer.setStore_list(null);
        customerRepository.save(newCustomer);
        userRepository.save(myUser);
    }

    public void registerStore(StoreDTO storeDTO) {
        MyUser myUser = new MyUser();
        myUser.setRole("store");
        myUser.setUsername(storeDTO.getUsername());
        String hashedPassword = new BCryptPasswordEncoder().encode(storeDTO.getPassword());
        myUser.setPassword(hashedPassword);

        MyUser saveUser = userRepository.save(myUser);

        Store store = new Store();
        store.setStoreName(storeDTO.getStoreName());
        store.setStoreNameOwner(storeDTO.getStoreNameOwner());
        store.setCity(storeDTO.getCity());
        store.setDistrict(storeDTO.getDistrict());
        store.setStreet(storeDTO.getStreet());
        store.setPhoneNumber(storeDTO.getPhoneNumber());
        store.setEmail(storeDTO.getEmail());
        store.setCompanyRegisterUrl(storeDTO.getCompanyRegisterUrl());
        store.setActive(storeDTO.isActive());
        store.setCommercialLicense(storeDTO.getCommercialLicense());
        store.setUser(saveUser);
        storeRepository.save(store);
    }

}
