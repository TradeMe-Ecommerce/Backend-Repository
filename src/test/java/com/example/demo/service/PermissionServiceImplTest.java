package com.example.demo.service;

import com.example.demo.entity.Permission;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.service.PermissionService;
import com.example.demo.service.impl.PermissionServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class PermissionServiceImplTest {

    @Mock
    private PermissionRepository permissionRepository;

    @InjectMocks
    private PermissionServiceImpl permissionService;

    @Test
    public void testCreatePermission() {
        Permission permission = new Permission();
        permission.setName("READ");
        permission.setDescription("Permission to read data");

        // Simulamos que el repositorio asigna un ID al guardar el permiso
        when(permissionRepository.save(any(Permission.class))).thenAnswer(invocation -> {
            Permission saved = invocation.getArgument(0);
            saved.setId(1L);
            return saved;
        });

        Permission created = permissionService.createPermission(permission);
        assertNotNull(created);
        assertEquals(1L, created.getId());
    }

    @Test
    public void testFindAllPermissions() {
        List<Permission> permissions = new ArrayList<>();
        Permission p1 = new Permission();
        p1.setId(1L);
        p1.setName("READ");
        Permission p2 = new Permission();
        p2.setId(2L);
        p2.setName("WRITE");
        permissions.add(p1);
        permissions.add(p2);

        when(permissionRepository.findAll()).thenReturn(permissions);

        List<Permission> result = permissionService.findAll();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("READ", result.get(0).getName());
        assertEquals("WRITE", result.get(1).getName());
    }

    @Test
    public void testUpdatePermission() {
        Permission permission = new Permission();
        permission.setId(1L);
        permission.setName("READ");
        permission.setDescription("Permission to read data");

        // Simulamos que el permiso existe
        when(permissionRepository.existsById(1L)).thenReturn(true);

        // Simulamos la actualización: modificamos la descripción
        permission.setDescription("Updated permission to read data");
        when(permissionRepository.save(any(Permission.class))).thenReturn(permission);

        Permission updated = permissionService.updatePermission(permission);
        assertNotNull(updated);
        assertEquals("Updated permission to read data", updated.getDescription());
    }

    @Test
    public void testUpdatePermissionNotFound() {
        Permission permission = new Permission();
        permission.setId(99L);
        permission.setName("Nonexistent");

        when(permissionRepository.existsById(99L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            permissionService.updatePermission(permission);
        });
        assertEquals("Permission not found", exception.getMessage());
    }

    @Test
    public void testDeletePermission() {
        Long permissionId = 1L;
        // No es necesario simular un comportamiento específico, solo verificar la llamada
        permissionService.deletePermission(permissionId);
        verify(permissionRepository, times(1)).deleteById(permissionId);
    }
}
