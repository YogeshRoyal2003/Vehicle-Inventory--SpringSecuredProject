package com.example.vehicleinventory.service;

import com.example.vehicleinventory.model.User;
import com.example.vehicleinventory.model.UserPrincipal;
import com.example.vehicleinventory.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepo repo;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = repo.findByUsername(username);
        if(user==null){
            System.out.println("User error 404");
            throw new UsernameNotFoundException("User error 404");
        }
        return new UserPrincipal(user);
    }
}
