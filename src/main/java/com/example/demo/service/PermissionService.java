package com.example.demo.service;

import com.example.demo.entity.Permission;

import java.util.List;
import java.util.Set;

public interface PermissionService {
    List<Permission> findAll();
    Permission createPermission(Permission permission);
    Permission updatePermission(Permission permission);
    void deletePermission(Long id);
    Set<Permission> findByIds(List<Long> permissions);
}
