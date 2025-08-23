package com.example.demo.service.impl;

import com.example.demo.entity.Role;
import com.example.demo.entity.User;
import com.example.demo.repository.RolRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RolRepository rolRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public List<Role> findAll() {
        return rolRepository.findAll();
    }

    @Override
    public Role createRole(Role role) {
        if (rolRepository.findByName(role.getName()).isEmpty()) {
            role.setName("ROLE_" + role.getName());
            return rolRepository.save(role);
        } else {
            throw new RuntimeException("Role already exists");
        }
    }

    @Override
    public Role updateRole(Role role) {
        if (!rolRepository.existsById(role.getId())) {
            throw new RuntimeException("Role not found");
        }
        return rolRepository.save(role);
    }

    @Override
    public void deleteRole(long id) {
        if (!rolRepository.existsById(id)) {
            throw new RuntimeException("Role not found");
        } else {
            List<User> users = (List<User>) userRepository.findAllByRole(rolRepository.findById(id).get());
            for (User user : users) {
                user.setRole(rolRepository.findById(2L).get()); // Set the default role with ID 2
                userRepository.save(user);
            }
            rolRepository.deleteById(id);
        }
    }
        
    @Override
    public Role findById(long id) {
        return rolRepository.findById(id).orElseThrow(
                () -> new RuntimeException("Role not found" + id));
    }

}
