package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.repository.RolRepository;
import com.example.demo.service.impl.RoleServiceImpl;
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
public class RoleServiceImplTest {

    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private RoleServiceImpl rolService;

    private Role role;

    @BeforeEach
    void setUp() {
        role = new Role();
        role.setId(1L);
        role.setName("ROLE_ADMIN");
        role.setDescription("Administrator role");
    }

    @Test
    public void testCreateRole() {
        // Arrange
        when(rolRepository.save(any(Role.class))).thenReturn(role);

        // Act
        Role created = rolService.createRole(new Role());

        // Assert
        assertNotNull(created);
        assertEquals(role.getId(), created.getId());
        verify(rolRepository, times(1)).save(any(Role.class));
    }

    @Test
    public void testFindAllRoles() {
        // Arrange
        Role anotherRole = new Role();
        anotherRole.setId(2L);
        anotherRole.setName("ROLE_USER");
        when(rolRepository.findAll()).thenReturn(List.of(role, anotherRole));

        // Act
        List<Role> result = rolService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("ROLE_ADMIN", result.get(0).getName());
    }

    @Test
    public void testUpdateRole() {
        // Arrange
        when(rolRepository.findById(1L)).thenReturn(Optional.of(role));
        when(rolRepository.save(any(Role.class))).thenReturn(role);

        Role roleToUpdate = new Role();
        roleToUpdate.setId(1L);
        roleToUpdate.setDescription("Updated description");

        // Act
        Role updated = rolService.updateRole(roleToUpdate);

        // Assert
        assertNotNull(updated);
        assertEquals("Updated description", updated.getDescription());
        verify(rolRepository, times(1)).findById(1L);
        verify(rolRepository, times(1)).save(any(Role.class));
    }

    @Test
    public void testUpdateRoleNotFound() {
        // Arrange
        when(rolRepository.findById(99L)).thenReturn(Optional.empty());
        Role nonExistentRole = new Role();
        nonExistentRole.setId(99L);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            rolService.updateRole(nonExistentRole);
        });

        assertEquals("Role not found with id: 99", exception.getMessage());
        verify(rolRepository, times(1)).findById(99L);
        verify(rolRepository, never()).save(any(Role.class));
    }

    @Test
    public void testDeleteRole() {
        // Arrange
        Long rolId = 1L;
        when(rolRepository.existsById(rolId)).thenReturn(true);
        doNothing().when(rolRepository).deleteById(rolId);

        // Act
        rolService.deleteRole(rolId);

        // Assert
        verify(rolRepository, times(1)).deleteById(rolId);
    }

    @Test
    public void testDeleteRoleNotFound() {
        // Arrange
        Long rolId = 99L;
        when(rolRepository.existsById(rolId)).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            rolService.deleteRole(rolId);
        });
        assertEquals("Role not found with id: 99", exception.getMessage());
        verify(rolRepository, never()).deleteById(anyLong());
    }
}