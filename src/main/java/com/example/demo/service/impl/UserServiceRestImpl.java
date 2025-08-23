package com.example.demo.service.impl;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.example.demo.DTO.RegisterRequest;
import com.example.demo.entity.*;
import com.example.demo.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.demo.DTO.InventoryDTO;
import com.example.demo.DTO.UserDTO;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.InventoryMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserServiceRest;

@Service
public class UserServiceRestImpl implements UserServiceRest {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private InventoryMapper inventoryMapper;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Override
    public List<UserDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDTO)
                .toList();
    }

    @Override
    public UserDTO getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDTO)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario " + id + " no existe"));
    }

    @Override
    public UserDTO getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toDTO)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario " + email + " no existe"));
    }

    @Override
    public UserDTO createUser(UserDTO dto) {

        if (dto == null || dto.getEmail() == null)
            throw new BadRequestException("E-mail es obligatorio");

        if (userRepository.findByEmail(dto.getEmail()).isPresent())
            throw new BadRequestException("El e-mail ya está registrado");

        User entity = userMapper.toEntity(dto);
        entity.setPassword(encoder.encode("ChangeMe!23"));
        entity.setDate(Date.valueOf(LocalDate.now()));

        return userMapper.toDTO(userRepository.save(entity));
    }

    @Override
    public UserDTO updateUser(UserDTO dto) {

        if (dto == null || dto.getId() == null)
            throw new BadRequestException("id es obligatorio");

        User existing = userRepository.findById(dto.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario " + dto.getId() + " no existe"));

        existing.setName(dto.getName());
        existing.setPhone(dto.getPhone());

        return userMapper.toDTO(userRepository.save(existing));
    }

    @Override
    public void deleteUser(Long id) {
        User u = userRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Usuario " + id + " no existe"));
        userRepository.delete(u);
    }

    @Override
    public void addInventoryToUser(Long userId, Long productId, int stock) {

        if (stock <= 0) {
            throw new BadRequestException("El stock debe ser mayor que cero");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario " + userId + " no existe"));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found: " + productId));

        Set<Inventory> inventories = user.getInventories();
        Optional<Inventory> existingInventory = inventories.stream()
                .filter(inv -> inv.getProduct().getId() == productId)
                .findFirst();

        if (existingInventory.isPresent()) {
            Inventory inventory = existingInventory.get();
            inventories.remove(inventory);
            inventory.setStock(inventory.getStock() + stock);
            inventory = inventoryRepository.save(inventory);
            inventories.add(inventory);
        } else {
            Inventory newInventory = new Inventory();
            newInventory.setProduct(product);
            newInventory.setStock(stock);
            newInventory.setUser(user);
            newInventory = inventoryRepository.save(newInventory);
            inventories.add(newInventory);
            user.setInventories(inventories);
        }
        userRepository.save(user);
    }

    @Override
    public void removeInventoryFromUser(Long userId, Long productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario " + userId + " no existe"));

        Set<Inventory> inventories = user.getInventories();
        Inventory inventoryToRemove = inventories.stream()
                .filter(inv -> inv.getProduct().getId() == productId)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Inventario para producto " + productId + " no encontrado"));

        inventories.remove(inventoryToRemove);
        user.setInventories(inventories);
        inventoryRepository.delete(inventoryToRemove);
        userRepository.save(user);

    }

    @Override
    public void updateInventoryForUser(Long userId, Long productId, int stock) {
        if (stock <= 0) {
            throw new BadRequestException("El stock debe ser mayor que cero");
        }

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario " + userId + " no existe"));


        Set<Inventory> inventories = user.getInventories();
        Inventory inventoryToUpdate = inventories.stream()
                .filter(inv -> inv.getProduct().getId() == productId)
                .findFirst()
                .orElseThrow(() -> new ResourceNotFoundException("Inventario para producto " + productId + " no encontrado"));
        inventories.remove(inventoryToUpdate);

        inventoryToUpdate.setStock(stock);
        inventoryToUpdate = inventoryRepository.save(inventoryToUpdate);
        inventories.add(inventoryToUpdate);
        user.setInventories(inventories);
        userRepository.save(user);
    }

    @Override
    public List<UserDTO> searchUsers(String query) {
        List<User> users = userRepository.findByNameContainingIgnoreCase(query);
        return users.stream()
                .map(userMapper::toDTO)
                .toList();
    }
    public Set<InventoryDTO> getUserInventories(Long userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("Usuario " + userId + " no existe"));

        Set<Inventory> inventories = user.getInventories();
        if (inventories.isEmpty()) {
            throw new ResourceNotFoundException("No hay inventarios para el usuario " + userId);
        }
        return inventories.stream()
                .map(inventoryMapper::toDTO)
                .collect(Collectors.toSet());

    }

    @Override
    public List<InventoryDTO> getInventoriesByProductId(Long productId) {
        if (productId == null) {
            throw new BadRequestException("El ID del producto es obligatorio");
        }
        List<Inventory> inventories = inventoryRepository.findByProduct_Id(productId);
        if (inventories.isEmpty()) {
            throw new ResourceNotFoundException("No hay inventarios para el producto " + productId);
        }
        return inventories.stream()
                .map(inventoryMapper::toDTO)
                .collect(Collectors.toList());
    }


    @Override
    public User registerUser(RegisterRequest request) {
        if (userRepository.findByEmail(request.getUsername()).isPresent()) {
            throw new RuntimeException("El correo electrónico ya está en uso.");
        }

        Role userRole = roleRepository.findByName("ROLE_USER")
                .orElseThrow(() -> new RuntimeException("Error: Rol 'ROLE_USER' no encontrado."));

        User newUser = new User();
        newUser.setEmail(request.getUsername());
        newUser.setName(request.getName());
        newUser.setDate(Date.valueOf(LocalDate.now()));
        newUser.setPassword(passwordEncoder.encode(request.getPassword()));
        newUser.setRole(userRole);

        newUser = userRepository.save(newUser);

        History history = new History();
        history.setUser(newUser);
        history = historyRepository.save(history);

        Favorite favorite = new Favorite();
        favorite.setUser(newUser);
        favorite = favoriteRepository.save(favorite);

        ShoppingCart shoppingCart = new ShoppingCart();
        shoppingCart.setUser(newUser);
        shoppingCart = shoppingCartRepository.save(shoppingCart);


        newUser.setHistory(history);
        newUser.setFavorite(favorite);
        newUser.setShoppingCart(shoppingCart);

        return userRepository.save(newUser);
    }

}
