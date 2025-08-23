package com.example.demo.service;

import com.example.demo.entity.Role;
import com.example.demo.repository.RolRepository;
import com.example.demo.service.impl.RoleServiceImpl;
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
public class RoleServiceImplTest {

    @Mock
    private RolRepository rolRepository;

    @InjectMocks
    private RoleServiceImpl rolService;

    @Test
    public void testCreateRole() {
        Role role = new Role();
        role.setName("Admin");
        role.setDescription("Administrator role");

        // Simulamos que el repositorio asigna un ID al guardar
        when(rolRepository.save(any(Role.class))).thenAnswer(invocation -> {
            Role saved = invocation.getArgument(0);
            saved.setId(1L);
            return saved;
        });

        Role created = rolService.createRole(role);
        assertNotNull(created);
        assertEquals(1L, created.getId());
    }

    @Test
    public void testFindAllRoles() {
        List<Role> roles = new ArrayList<>();
        Role role1 = new Role();
        role1.setId(1L);
        role1.setName("Admin");
        Role role2 = new Role();
        role2.setId(2L);
        role2.setName("User");
        roles.add(role1);
        roles.add(role2);

        when(rolRepository.findAll()).thenReturn(roles);

        List<Role> result = rolService.findAll();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Admin", result.get(0).getName());
        assertEquals("User", result.get(1).getName());
    }

    @Test
    public void testUpdateRole() {
        Role role = new Role();
        role.setId(1L);
        role.setName("Admin");
        role.setDescription("Administrator role");

        // Simulamos que el role existe
        when(rolRepository.existsById(1L)).thenReturn(true);
        // Simulamos la actualización (por ejemplo, modificamos la descripción)
        role.setDescription("Updated Administrator role");
        when(rolRepository.save(any(Role.class))).thenReturn(role);

        Role updated = rolService.updateRole(role);
        assertNotNull(updated);
        assertEquals("Updated Administrator role", updated.getDescription());
    }

    @Test
    public void testUpdateRoleNotFound() {
        Role role = new Role();
        role.setId(99L);
        role.setName("Nonexistent");

        when(rolRepository.existsById(99L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            rolService.updateRole(role);
        });
        assertEquals("Role not found", exception.getMessage());
    }

    @Test
    public void testDeleteRole() {
        Long rolId = 1L;
        rolService.deleteRole(rolId);
        verify(rolRepository, times(1)).deleteById(rolId);
    }
}
