package com.example.demo.service.impl;

import com.example.demo.entity.Permission;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.service.PermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class PermissionServiceImpl implements PermissionService {

    @Autowired
    private PermissionRepository permissionRepository;

    @Override
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }

    @Override
    public Permission createPermission(Permission user) {
        if (permissionRepository.existsById(user.getId())) {
            throw new RuntimeException("Permission already exists");
        }
        return permissionRepository.save(user);
    }

    @Override
    public Permission updatePermission(Permission permission) {
        if (!permissionRepository.existsById(permission.getId())) {
            throw new RuntimeException("Permission not found");
        }
        return permissionRepository.save(permission);
    }

    @Override
    public void deletePermission(Long id) {
        permissionRepository.deleteById(id);
    }

    @Override
    public Set<Permission> findByIds(List<Long> permissions) {
        return new HashSet<>(permissionRepository.findAllById(permissions));
    }

}
