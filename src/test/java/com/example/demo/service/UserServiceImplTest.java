package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.*;


import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    public void testCreateUser() {
        // Configuramos un usuario de prueba
        User user = new User();
        user.setName("Alice");
        user.setEmail("alice@example.com");
        // Aquí podrías configurar otros atributos necesarios (password, phone, date, rol, etc.)

        // Simulamos que el repositorio asigna un ID al guardar el usuario
        when(userRepository.save(any(User.class))).thenAnswer(invocation -> {
            User saved = invocation.getArgument(0);
            saved.setId(1L);
            return saved;
        });

        User created = userService.createUser(user);
        assertNotNull(created);
        assertEquals(1L, created.getId());
    }

    @Test
    public void testFindAllUsers() {
        // Preparamos una lista de usuarios de prueba
        List<User> users = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setName("Alice");
        user1.setEmail("alice@example.com");
        User user2 = new User();
        user2.setId(2L);
        user2.setName("Bob");
        user2.setEmail("bob@example.com");
        users.add(user1);
        users.add(user2);

        // Simulamos que el método findAll del repositorio devuelve la lista de usuarios
        when(userRepository.findAll()).thenReturn(users);

        List<User> result = userService.findAll();
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getName());
        assertEquals("Bob", result.get(1).getName());
    }
    @Test
    public void testUpdateUser() {
        User user = new User();
        user.setId(1L);
        user.setName("Alice");
        user.setEmail("alice@example.com");
        // Simula que el usuario ya existe
        when(userRepository.existsById(1L)).thenReturn(true);
        // Simula que al guardar se actualiza el nombre
        user.setName("Alice Updated");
        when(userRepository.save(any(User.class))).thenReturn(user);

        User updated = userService.updateUser(user);
        assertNotNull(updated);
        assertEquals("Alice Updated", updated.getName());
    }

    @Test
    public void testUpdateUserNotFound() {
        User user = new User();
        user.setId(99L);
        user.setName("Nonexistent");
        when(userRepository.existsById(99L)).thenReturn(false);

        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.updateUser(user);
        });
        assertEquals("User not found", exception.getMessage());
    }

    @Test
    public void testDeleteUser() {
        Long userId = 1L;
        // Simulamos que el usuario con ID 1 existe
        when(userRepository.existsById(userId)).thenReturn(true);

        // Llamamos al método
        userService.deleteUser(userId);

        // Verificamos que se invoque deleteById con el ID correcto
        verify(userRepository, times(1)).deleteById(userId);
    }
}
