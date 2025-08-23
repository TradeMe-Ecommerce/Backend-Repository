package com.example.demo.mapper;

import com.example.demo.DTO.CartListingDTO;
import com.example.demo.entity.CartListing;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface CartListingMapper {

    @Mapping(source= "shoppingCartId", target = "shoppingCart.id")
    @Mapping(source = "productId", target = "product.id")
    CartListing toEntity(CartListingDTO cartListing);

    @Mapping(source = "shoppingCart.id", target = "shoppingCartId")
    @Mapping(source = "product.id", target = "productId")
    CartListingDTO toDTO(CartListing cartListing);
}
