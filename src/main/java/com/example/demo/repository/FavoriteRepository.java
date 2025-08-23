package com.example.demo.repository;

import com.example.demo.entity.Favorite;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface FavoriteRepository extends JpaRepository<Favorite, Long> {
    /** Devuelve el “wishlist” de un usuario (siempre hay uno por usuario) */
    Optional<Favorite> findByUser_Id(Long userId);

}
