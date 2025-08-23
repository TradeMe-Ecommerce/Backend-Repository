package com.example.demo.service.impl;

import com.example.demo.entity.User;
import com.example.demo.security.CustomUserDetails;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserService userService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User user = userService.findByEmail(username);
            if (user == null) {
                throw new UsernameNotFoundException("Usuario no encontrado: " + username);
            }
            return new CustomUserDetails(user);
        } catch (Exception e) {
            System.out.println("Error loading user: " + e.getMessage());
            throw new UsernameNotFoundException("Usuario no encontrado: " + username, e);
        }
    }
}