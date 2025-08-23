package com.example.demo.service;

import com.example.demo.DTO.ImageDTO;
import java.util.List;

public interface ImageService {
    ImageDTO findById(Long id);
    List<ImageDTO> findAll();
    List<ImageDTO> findByProductId(Long productId);
    ImageDTO create(ImageDTO dto);
    void delete(Long id);

    ImageDTO update(ImageDTO dto);
}
