package com.example.demo.mapper;

import com.example.demo.DTO.InventoryDTO;
import com.example.demo.entity.Inventory;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "productId", target = "product.id")
    Inventory toEntity(InventoryDTO inventory);

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "product.id", target = "productId")
    InventoryDTO toDTO(Inventory inventory);
}
