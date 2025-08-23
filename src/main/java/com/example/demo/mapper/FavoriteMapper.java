package com.example.demo.mapper;

import com.example.demo.DTO.FavoriteDTO;
import com.example.demo.entity.Favorite;
import com.example.demo.util.ProductMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {ProductMapperHelper.class})
public interface FavoriteMapper {

    @Mapping(source = "id", target = "id")
    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "products", target = "productIds")
    FavoriteDTO toDTO(Favorite favorite);

    @Mapping(source = "id", target = "id")
    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "productIds", target = "products")
    Favorite toEntity(FavoriteDTO dto);

}
