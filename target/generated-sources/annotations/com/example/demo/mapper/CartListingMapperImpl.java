package com.example.demo.mapper;

import com.example.demo.DTO.CartListingDTO;
import com.example.demo.entity.CartListing;
import com.example.demo.entity.Product;
import com.example.demo.entity.ShoppingCart;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-25T06:54:22-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class CartListingMapperImpl implements CartListingMapper {

    @Override
    public CartListing toEntity(CartListingDTO cartListing) {
        if ( cartListing == null ) {
            return null;
        }

        CartListing cartListing1 = new CartListing();

        cartListing1.setShoppingCart( cartListingDTOToShoppingCart( cartListing ) );
        cartListing1.setProduct( cartListingDTOToProduct( cartListing ) );
        cartListing1.setId( cartListing.getId() );
        cartListing1.setQuantity( cartListing.getQuantity() );

        return cartListing1;
    }

    @Override
    public CartListingDTO toDTO(CartListing cartListing) {
        if ( cartListing == null ) {
            return null;
        }

        CartListingDTO cartListingDTO = new CartListingDTO();

        cartListingDTO.setShoppingCartId( cartListingShoppingCartId( cartListing ) );
        cartListingDTO.setProductId( cartListingProductId( cartListing ) );
        cartListingDTO.setId( cartListing.getId() );
        if ( cartListing.getQuantity() != null ) {
            cartListingDTO.setQuantity( cartListing.getQuantity() );
        }

        return cartListingDTO;
    }

    protected ShoppingCart cartListingDTOToShoppingCart(CartListingDTO cartListingDTO) {
        if ( cartListingDTO == null ) {
            return null;
        }

        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.setId( cartListingDTO.getShoppingCartId() );

        return shoppingCart;
    }

    protected Product cartListingDTOToProduct(CartListingDTO cartListingDTO) {
        if ( cartListingDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( cartListingDTO.getProductId() );

        return product;
    }

    private long cartListingShoppingCartId(CartListing cartListing) {
        if ( cartListing == null ) {
            return 0L;
        }
        ShoppingCart shoppingCart = cartListing.getShoppingCart();
        if ( shoppingCart == null ) {
            return 0L;
        }
        long id = shoppingCart.getId();
        return id;
    }

    private long cartListingProductId(CartListing cartListing) {
        if ( cartListing == null ) {
            return 0L;
        }
        Product product = cartListing.getProduct();
        if ( product == null ) {
            return 0L;
        }
        long id = product.getId();
        return id;
    }
}
