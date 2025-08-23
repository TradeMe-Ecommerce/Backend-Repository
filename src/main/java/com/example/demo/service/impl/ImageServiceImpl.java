package com.example.demo.service.impl;

import com.example.demo.DTO.ImageDTO;
import com.example.demo.entity.Image;
import com.example.demo.entity.Product;
import com.example.demo.exception.BadRequestException;
import com.example.demo.exception.ResourceNotFoundException;
import com.example.demo.mapper.ImageMapper;
import com.example.demo.repository.ImageRepository;
import com.example.demo.repository.ProductRepository;
import com.example.demo.service.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private  ImageMapper imageMapper;

    @Override
    public ImageDTO findById(Long id) {
        return imageRepository.findById(id)
                .map(imageMapper::toDTO)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Imagen " + id + " no existe"));
    }

    @Override
    public List<ImageDTO> findAll() {
        return imageRepository.findAll()
                .stream()
                .map(imageMapper::toDTO)
                .toList();
    }

    @Override
    public List<ImageDTO> findByProductId(Long productId) {
        if (!productRepository.existsById(productId))
            throw new ResourceNotFoundException("Producto " + productId + " no existe");
        return imageRepository.findByProduct_Id(productId)
                .stream()
                .map(imageMapper::toDTO)
                .toList();
    }

    @Override
    public ImageDTO create(ImageDTO dto) {
        if (dto == null || dto.getProductId() == 0 || dto.getImage() == null)
            throw new BadRequestException("productId e image son obligatorios");

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Producto " + dto.getProductId() + " no existe"));

        Image entity = imageMapper.toEntity(dto);
        entity.setProduct(product);

        return imageMapper.toDTO(imageRepository.save(entity));
    }

    @Override
    public void delete(Long id) {
        Image img = imageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Image not found"));
        imageRepository.delete(img);
    }

    @Override
    public ImageDTO update(ImageDTO dto) {

        if (dto == null || dto.getId() == 0)
            throw new BadRequestException("id de la imagen es obligatorio");

        Image existing = imageRepository.findById(dto.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Imagen " + dto.getId() + " no existe"));

        /* opcionalmente cambiar de producto */
        if (dto.getProductId() != 0 && dto.getProductId() != existing.getProduct().getId()) {
            Product newProduct = productRepository.findById(dto.getProductId())
                    .orElseThrow(() ->
                            new ResourceNotFoundException(
                                    "Producto " + dto.getProductId() + " no existe"));
            existing.setProduct(newProduct);
        }
        if (dto.getImage() != null)
            existing.setImage(dto.getImage());

        return imageMapper.toDTO(imageRepository.save(existing));
    }
}
