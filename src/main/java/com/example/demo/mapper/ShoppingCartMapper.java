package com.example.demo.mapper;

import com.example.demo.DTO.ShoppingCartDTO;
import com.example.demo.entity.ShoppingCart;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = CartListingMapper.class)
public interface ShoppingCartMapper {

    @Mapping(source = "user.id", target = "userId")
    ShoppingCartDTO toDTO(ShoppingCart cart);

    @Mapping(source = "userId", target = "user.id")
    ShoppingCart toEntity(ShoppingCartDTO dto);
}
