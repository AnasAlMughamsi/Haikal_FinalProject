package com.example.finalproject.service;

import com.example.finalproject.api.ApiException;
import com.example.finalproject.dto.CustomerDTO;
import com.example.finalproject.dto.StoreDTO;
import com.example.finalproject.model.Customer;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.model.Product;
import com.example.finalproject.model.Store;
import com.example.finalproject.repository.CustomerRepository;
import com.example.finalproject.repository.MyUserRepository;
import com.example.finalproject.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {

    private final MyUserRepository myUserRepository;
    private final StoreRepository storeRepository;
    private final CustomerRepository customerRepository;

    public List<Store> getAllStores() {
        return storeRepository.findAll();
    }

    public Store getStoreById(Integer id) {
        Store store = storeRepository.findStoreById(id);
        if (store == null) {
            throw new ApiException("store not found");
        }
        return store;
    }

    public void addStore(Store store) {
        // TODO: I think need to check if I need to add User before adding a Store or not
//        User user = new User();
//        user.setRole("store");
        storeRepository.save(store);
    }

    public void updateStore(Store updateStore, Integer id) {
        Store store = storeRepository.findStoreById(id);
        if (store == null) {
            throw new ApiException("store not found, wrong id");
        }
        updateStore.setId(id);
        storeRepository.save(updateStore);
    }

    public void deleteStore(Integer id) {
        Store store = storeRepository.findStoreById(id);
        if (store == null) {
            throw new ApiException("Wrong store id");
        }
        storeRepository.delete(store);
    }

    public void changeStoreStatus(Integer store_id) {
        Store store = storeRepository.findStoreById(store_id);
        if (store == null) {
            throw new ApiException("Store not found");
        }
        if (!store.isActive()) {
            store.setActive(true);
        } else {
            store.setActive(false);
        }
        storeRepository.save(store);
    }
}
