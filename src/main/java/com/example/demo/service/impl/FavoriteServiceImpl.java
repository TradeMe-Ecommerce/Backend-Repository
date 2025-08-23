package com.example.demo.service.impl;

import com.example.demo.DTO.FavoriteDTO;
import com.example.demo.entity.Favorite;
import com.example.demo.entity.Product;
import com.example.demo.mapper.FavoriteMapper;
import com.example.demo.repository.FavoriteRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.FavoriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class FavoriteServiceImpl implements FavoriteService {

    @Autowired
    private FavoriteRepository favoriteRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private FavoriteMapper favoriteMapper;

    @Override
    public FavoriteDTO findById(Long id) {
        Favorite favorite = favoriteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Favorite not found"));
        return favoriteMapper.toDTO(favorite);
    }

    @Override
    public FavoriteDTO getFavoriteByUserId(Long userId) {
        Favorite favorite = favoriteRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Favorite not found"));
        return favoriteMapper.toDTO(favorite);
    }

    @Override
    public void addProductToFavorite(Long userId, Long productId) {
        Favorite favorite = favoriteRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Favorite not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        favorite.addProduct(product);
        favoriteRepository.save(favorite);
    }

    @Override
    public void removeProductFromFavorite(Long userId, Long productId) {
        Favorite favorite = favoriteRepository.findByUser_Id(userId)
                .orElseThrow(() -> new RuntimeException("Favorite not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        favorite.removeProduct(product);
        favoriteRepository.save(favorite);
    }

    @Override
    public List<FavoriteDTO> findAll() {
        return favoriteRepository.findAll().stream().map( favorite ->
                favoriteMapper.toDTO(favorite)).toList();
    }

    @Override
    public FavoriteDTO createFavorite(FavoriteDTO favoriteDTO) {
        return favoriteMapper.toDTO(favoriteRepository.save(favoriteMapper.toEntity(favoriteDTO)));
    }

    @Override
    public FavoriteDTO updateFavorite(FavoriteDTO favoriteDTO) {
        Favorite fav = favoriteRepository.findById(favoriteDTO.getId()).orElseThrow(
                () -> new RuntimeException("Favorite not found"));
        fav.setProducts(favoriteDTO.getProductIds().stream().map(
                productId -> productRepository.findById(productId)
                        .orElseThrow(() -> new RuntimeException("Product not found: " + productId))
                ).collect(Collectors.toSet())
        );
        return favoriteMapper.toDTO(favoriteRepository.save(fav));
    }

    @Override
    public void deleteFavorite(Long id) {
        Favorite favorite = favoriteRepository.findById(id).orElseThrow(() -> new RuntimeException("favorite not found"));
        favoriteRepository.delete(favorite);
    }
}
