package com.example.demo.service.impl;

import com.example.demo.DTO.CartListingDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.DTO.ShoppingCartDTO;
import com.example.demo.entity.CartListing;
import com.example.demo.entity.Product;
import com.example.demo.entity.ShoppingCart;
import com.example.demo.entity.User;
import com.example.demo.mapper.CartListingMapper;
import com.example.demo.mapper.ShoppingCartMapper;
import com.example.demo.repository.CartListingRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.repository.ShoppingCartRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.ShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {

    @Autowired
    private ShoppingCartRepository shoppingCartRepository;

    @Autowired
    private ShoppingCartMapper shoppingCartMapper;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartListingRepository cartListingRepository;

    @Autowired
    private CartListingMapper cartListingMapper;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public ShoppingCartDTO getCartByUserId(Long userId) {
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Shopping cart not found for user: " + userId));
        return shoppingCartMapper.toDTO(cart);
    }

    @Override
    public void addProductToCart(Long userId, Long productId, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        ShoppingCart cart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Shopping cart not found for user: " + userId));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found: " + productId));

        Set<CartListing> listings = cart.getCartListings();
        Optional<CartListing> existingListing = listings.stream()
                .filter(cl -> cl.getProduct().getId() == productId)
                .findFirst();

        if (existingListing.isPresent()) {
            CartListing listing = existingListing.get();
            cart.removeListing(listing);
            listing.setQuantity(listing.getQuantity() + quantity);
            listing = cartListingRepository.save(listing);
            listings.add(listing);
            cart.setCartListings(listings);
        } else {
            CartListing newListing = new CartListing();
            newListing.setProduct(product);
            newListing.setQuantity(quantity);
            newListing.setShoppingCart(cart);
            newListing = cartListingRepository.save(newListing);
            listings.add(newListing);
            cart.setCartListings(listings);
        }
        shoppingCartRepository.save(cart);
    }

    @Override
    public void updateProductQuantity(Long userId, Long productId, int quantity) {

        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }

        ShoppingCart cart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Shopping cart not found for user: " + userId));

        if (!productRepository.existsById(productId)) {
            throw new RuntimeException("Product not found: " + productId);
        }

        Set<CartListing> listings = cart.getCartListings();
        CartListing listing = listings.stream()
                .filter(cl -> cl.getProduct().getId() == productId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not in cart: " + productId));
        cart.getCartListings().remove(listing);

        listing.setQuantity(quantity);
        listing = cartListingRepository.save(listing);
        listings.add(listing);
        cart.setCartListings(listings);
        shoppingCartRepository.save(cart);
    }

    @Override
    public void removeProductFromCart(Long userId, Long productId) {
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Shopping cart not found for user: " + userId));

        Set<CartListing> listings = cart.getCartListings();
        long listing_id = listings.stream()
                .filter(cl -> cl.getProduct().getId() == productId)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not in cart: " + productId)).getId();

        CartListing listing = cartListingRepository.findById(listing_id).orElseThrow(
                () -> new RuntimeException("Listing not found: " + listing_id));

        cart.removeListing(listing);
        cartListingRepository.delete(listing);
        shoppingCartRepository.save(cart);
    }

    @Override
    public void clearCart(Long userId) {
        ShoppingCart cart = shoppingCartRepository.findByUserId(userId)
                .orElseThrow(() -> new RuntimeException("Shopping cart not found for user: " + userId));

        cart.getCartListings().clear();
        shoppingCartRepository.save(cart);
    }

    @Override
    public ShoppingCartDTO createShoppingCart(ShoppingCartDTO shoppingCartDTO) {
        Optional<User> userOptional = userRepository.findById(shoppingCartDTO.getUserId());
        if (userOptional.isPresent()) {
            if (userOptional.get().getShoppingCart().getCartListings().isEmpty()) {
                ShoppingCart shoppingCart = shoppingCartMapper.toEntity(shoppingCartDTO);
                return shoppingCartMapper.toDTO(shoppingCartRepository.save(shoppingCart));
            } else {
                throw new RuntimeException("User already has a shopping cart");
            }
        } else {
            throw new RuntimeException("User not found");
        }
    }


    @Override
    public List<ShoppingCartDTO> findAll() {
        return shoppingCartRepository
                .findAll()
                .stream()
                .map(shoppingCartMapper::toDTO)
                .toList();
    }

    @Override
    public List<CartListingDTO> getCartListingsByIds(List<Long> ids) {
        List<CartListing> listings = cartListingRepository.findAllById(ids);
        return listings.stream()
                .map(listing -> cartListingMapper.toDTO(listing))
                .toList();
    }
}
