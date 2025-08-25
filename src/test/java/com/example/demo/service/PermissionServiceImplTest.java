package com.example.demo.service;

import com.example.demo.entity.Permission;
import com.example.demo.repository.PermissionRepository;
import com.example.demo.service.impl.PermissionServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PermissionServiceImplTest {

    @Mock
    private PermissionRepository permissionRepository;

    @InjectMocks
    private PermissionServiceImpl permissionService;

    private Permission permission;

    @BeforeEach
    void setUp() {
        permission = new Permission();
        permission.setId(1L);
        permission.setName("READ_PRIVILEGES");
        permission.setDescription("Permission to read data");
    }

    @Test
    public void testCreatePermission() {
        // Arrange
        when(permissionRepository.save(any(Permission.class))).thenReturn(permission);

        // Act
        Permission created = permissionService.createPermission(new Permission());

        // Assert
        assertNotNull(created);
        assertEquals(permission.getId(), created.getId());
        verify(permissionRepository, times(1)).save(any(Permission.class));
    }

    @Test
    public void testFindAllPermissions() {
        // Arrange
        Permission anotherPermission = new Permission();
        anotherPermission.setId(2L);
        anotherPermission.setName("WRITE_PRIVILEGES");
        when(permissionRepository.findAll()).thenReturn(List.of(permission, anotherPermission));

        // Act
        List<Permission> result = permissionService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("READ_PRIVILEGES", result.get(0).getName());
    }

    @Test
    public void testUpdatePermission() {
        // Arrange
        when(permissionRepository.findById(1L)).thenReturn(Optional.of(permission));
        when(permissionRepository.save(any(Permission.class))).thenReturn(permission);

        Permission permissionToUpdate = new Permission();
        permissionToUpdate.setId(1L);
        permissionToUpdate.setDescription("Updated description");

        // Act
        Permission updated = permissionService.updatePermission(permissionToUpdate);

        // Assert
        assertNotNull(updated);
        assertEquals("Updated description", updated.getDescription());
        verify(permissionRepository, times(1)).findById(1L);
        verify(permissionRepository, times(1)).save(any(Permission.class));
    }

    @Test
    public void testUpdatePermissionNotFound() {
        // Arrange
        when(permissionRepository.findById(99L)).thenReturn(Optional.empty());
        Permission nonExistentPermission = new Permission();
        nonExistentPermission.setId(99L);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            permissionService.updatePermission(nonExistentPermission);
        });

        assertEquals("Permission not found with id: 99", exception.getMessage());
        verify(permissionRepository, times(1)).findById(99L);
        verify(permissionRepository, never()).save(any(Permission.class));
    }

    @Test
    public void testDeletePermission() {
        // Arrange
        Long permissionId = 1L;
        when(permissionRepository.existsById(permissionId)).thenReturn(true);
        doNothing().when(permissionRepository).deleteById(permissionId);

        // Act
        permissionService.deletePermission(permissionId);

        // Assert
        verify(permissionRepository, times(1)).deleteById(permissionId);
    }

    @Test
    public void testDeletePermissionNotFound() {
        // Arrange
        Long permissionId = 99L;
        when(permissionRepository.existsById(permissionId)).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            permissionService.deletePermission(permissionId);
        });

        assertEquals("Permission not found with id: 99", exception.getMessage());
        verify(permissionRepository, never()).deleteById(anyLong());
    }
}