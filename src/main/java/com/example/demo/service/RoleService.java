package com.example.demo.service;

import com.example.demo.entity.Role;

import java.util.List;

public interface RoleService {
    List<Role> findAll();

    Role createRole(Role role);

    Role updateRole(Role role);

    void deleteRole(long id);

    Role findById(long id);

}
