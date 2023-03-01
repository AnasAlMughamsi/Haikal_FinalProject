package com.example.finalproject.service;

import com.example.finalproject.api.ApiException;
import com.example.finalproject.model.MyUser;
import com.example.finalproject.repository.MyUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MyUserDetailsService implements UserDetailsService {

    private final MyUserRepository myUserRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        MyUser myUser = myUserRepository.findMyUserByUsername(username);
        if(myUser == null) {
            throw new ApiException("User not found!");
        }
        return myUser;
    }
}
