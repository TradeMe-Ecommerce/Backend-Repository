package com.example.demo.mapper;

import com.example.demo.DTO.ImageDTO;
import com.example.demo.entity.Image;
import com.example.demo.util.ProductMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

@Mapper(componentModel = "spring")
public interface ImageMapper {

    @Mapping(source = "product.id", target = "productId")
    ImageDTO toDTO(Image image);

    @Mapping(source = "productId", target = "product.id")
    Image toEntity(ImageDTO imageDTO);
}
