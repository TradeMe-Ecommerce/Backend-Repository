package com.example.demo.repository;

import com.example.demo.entity.Favorite;
import com.example.demo.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<Image, Long> {
    /** Todas las listas de favoritos que contienen un producto concreto */
    List<Image> findByProduct_Id(Long productId);
}
