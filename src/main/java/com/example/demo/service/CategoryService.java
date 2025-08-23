package com.example.demo.service;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.entity.Category;

import java.util.List;

public interface CategoryService {
    // Define methods for category service
    List<CategoryDTO> findAll();
    CategoryDTO findById(Long id);
    CategoryDTO createCategory(CategoryDTO category);
    CategoryDTO updateCategory(CategoryDTO category);
    void deleteCategory(Long id);
}
