package com.example.demo.service.impl;

import com.example.demo.DTO.RegisterRequest;
import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.exception.EmailAlreadyExistsException;
import com.example.demo.exception.WeakPasswordException;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RoleService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.sql.Date;
import java.util.List;
import java.util.Set;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public User createUser(User user) {

        if (userRepository.findByEmail(user.getEmail()).isPresent())
            throw new EmailAlreadyExistsException();


        if (!isStrongPassword(user.getPassword()))
            throw new WeakPasswordException();

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(roleService.findById(2));
        user.setDate(Date.valueOf(LocalDate.now()));
        return userRepository.save(user);
    }

    private boolean isStrongPassword(String pwd) {
        return pwd != null && pwd.matches(
                "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z]).{8,}$");
    }

    @Override
    public User updateUser(User user) {
        if (findById(user.getId()) == null) {
            throw new RuntimeException("User not found");
        } else {
            return userRepository.save(user);
        }
    }

    @Override
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        } else {
            throw new RuntimeException("User no encontrado con ID: " + id);
        }
    }

    @Override
    public User findByEmail(String email) {
        // devuelve null si no existe; no lanza excepción
        return userRepository.findByEmail(email).orElse(null);
    }


    @Override
    public User findById(Long id) {
        if (userRepository.findById(id).isPresent()) {
            return userRepository.findById(id).get();
        } else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    public User setRole(User user, long role) {
        if (roleService.findById(role) == null) {
            throw new RuntimeException("Role not found");
        } else {
            user.setRole(roleService.findById(role));
            return userRepository.save(user);
        }
    }
}

