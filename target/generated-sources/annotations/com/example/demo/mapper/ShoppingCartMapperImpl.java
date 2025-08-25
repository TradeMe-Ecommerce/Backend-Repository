package com.example.demo.mapper;

import com.example.demo.DTO.CartListingDTO;
import com.example.demo.DTO.ShoppingCartDTO;
import com.example.demo.entity.CartListing;
import com.example.demo.entity.ShoppingCart;
import com.example.demo.entity.User;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-24T20:29:30-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class ShoppingCartMapperImpl implements ShoppingCartMapper {

    @Autowired
    private CartListingMapper cartListingMapper;

    @Override
    public ShoppingCartDTO toDTO(ShoppingCart cart) {
        if ( cart == null ) {
            return null;
        }

        ShoppingCartDTO shoppingCartDTO = new ShoppingCartDTO();

        shoppingCartDTO.setUserId( cartUserId( cart ) );
        shoppingCartDTO.setId( cart.getId() );
        shoppingCartDTO.setCartListings( cartListingSetToCartListingDTOSet( cart.getCartListings() ) );

        return shoppingCartDTO;
    }

    @Override
    public ShoppingCart toEntity(ShoppingCartDTO dto) {
        if ( dto == null ) {
            return null;
        }

        ShoppingCart shoppingCart = new ShoppingCart();

        shoppingCart.setUser( shoppingCartDTOToUser( dto ) );
        shoppingCart.setId( dto.getId() );
        shoppingCart.setCartListings( cartListingDTOSetToCartListingSet( dto.getCartListings() ) );

        return shoppingCart;
    }

    private long cartUserId(ShoppingCart shoppingCart) {
        if ( shoppingCart == null ) {
            return 0L;
        }
        User user = shoppingCart.getUser();
        if ( user == null ) {
            return 0L;
        }
        long id = user.getId();
        return id;
    }

    protected Set<CartListingDTO> cartListingSetToCartListingDTOSet(Set<CartListing> set) {
        if ( set == null ) {
            return null;
        }

        Set<CartListingDTO> set1 = new LinkedHashSet<CartListingDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CartListing cartListing : set ) {
            set1.add( cartListingMapper.toDTO( cartListing ) );
        }

        return set1;
    }

    protected User shoppingCartDTOToUser(ShoppingCartDTO shoppingCartDTO) {
        if ( shoppingCartDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( shoppingCartDTO.getUserId() );

        return user;
    }

    protected Set<CartListing> cartListingDTOSetToCartListingSet(Set<CartListingDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<CartListing> set1 = new LinkedHashSet<CartListing>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CartListingDTO cartListingDTO : set ) {
            set1.add( cartListingMapper.toEntity( cartListingDTO ) );
        }

        return set1;
    }
}
