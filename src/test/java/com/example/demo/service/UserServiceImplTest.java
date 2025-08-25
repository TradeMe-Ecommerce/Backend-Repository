package com.example.demo.service;

import com.example.demo.entity.User;
import com.example.demo.exception.WeakPasswordException; // Suponiendo que tienes esta excepción
import com.example.demo.repository.UserRepository;
import com.example.demo.service.impl.UserServiceImpl;
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
public class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setName("Alice");
        user.setEmail("alice@example.com");
        user.setPassword("StrongPassword123!");
    }

    @Test
    public void testCreateUser() {
        // Arrange
        when(userRepository.save(any(User.class))).thenReturn(user);

        // Act
        User created = userService.createUser(user);

        // Assert
        assertNotNull(created);
        assertEquals(user.getId(), created.getId());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testCreateUser_WithWeakPassword() {
        // Arrange
        User userWithWeakPassword = new User();
        userWithWeakPassword.setPassword("123"); // Contraseña débil

        // Act & Assert: Suponiendo que tu servicio lanza esta excepción
        assertThrows(WeakPasswordException.class, () -> {
            userService.createUser(userWithWeakPassword);
        });

        verify(userRepository, never()).save(any(User.class));
    }


    @Test
    public void testFindAllUsers() {
        // Arrange
        User anotherUser = new User();
        anotherUser.setId(2L);
        anotherUser.setName("Bob");
        when(userRepository.findAll()).thenReturn(List.of(user, anotherUser));

        // Act
        List<User> result = userService.findAll();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Alice", result.get(0).getName());
    }

    @Test
    public void testUpdateUser() {
        // Arrange
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(user);

        User userToUpdate = new User();
        userToUpdate.setId(1L);
        userToUpdate.setName("Alice Updated");

        // Act
        User updated = userService.updateUser(userToUpdate);

        // Assert
        assertNotNull(updated);
        assertEquals("Alice Updated", updated.getName());
        verify(userRepository, times(1)).findById(1L);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    public void testUpdateUserNotFound() {
        // Arrange
        when(userRepository.findById(99L)).thenReturn(Optional.empty());
        User nonExistentUser = new User();
        nonExistentUser.setId(99L);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.updateUser(nonExistentUser);
        });

        assertEquals("User not found with id: 99", exception.getMessage());
        verify(userRepository, times(1)).findById(99L);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    public void testDeleteUser() {
        // Arrange
        Long userId = 1L;
        when(userRepository.existsById(userId)).thenReturn(true);
        doNothing().when(userRepository).deleteById(userId);

        // Act
        userService.deleteUser(userId);

        // Assert
        verify(userRepository, times(1)).deleteById(userId);
    }

    @Test
    public void testDeleteUserNotFound() {
        // Arrange
        Long userId = 99L;
        when(userRepository.existsById(userId)).thenReturn(false);

        // Act & Assert
        Exception exception = assertThrows(RuntimeException.class, () -> {
            userService.deleteUser(userId);
        });

        assertEquals("User not found with id: 99", exception.getMessage());
        verify(userRepository, never()).deleteById(anyLong());
    }
}