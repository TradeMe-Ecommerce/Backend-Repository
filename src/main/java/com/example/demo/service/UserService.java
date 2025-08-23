package com.example.demo.service;

import com.example.demo.DTO.RegisterRequest;
import com.example.demo.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> findAll();
    
    User createUser(User user);

    User updateUser(User user);

    void deleteUser(Long id);

    User findByEmail(String username);

    User findById(Long id);

    User setRole(User user, long role);
}
