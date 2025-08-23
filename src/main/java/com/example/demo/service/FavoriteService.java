package com.example.demo.service;

import com.example.demo.DTO.FavoriteDTO;
import com.example.demo.entity.Favorite;

import java.util.List;

public interface FavoriteService {
    FavoriteDTO findById(Long id);
    FavoriteDTO getFavoriteByUserId(Long userId);
    void addProductToFavorite(Long userId, Long productId);
    void removeProductFromFavorite(Long userId, Long productId);
    List<FavoriteDTO> findAll();

    FavoriteDTO createFavorite(FavoriteDTO favoriteDTO);

    FavoriteDTO updateFavorite(FavoriteDTO favoriteDTO);

    void deleteFavorite(Long id);
}
