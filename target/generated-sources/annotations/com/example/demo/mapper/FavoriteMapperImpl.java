package com.example.demo.mapper;

import com.example.demo.DTO.FavoriteDTO;
import com.example.demo.entity.Favorite;
import com.example.demo.entity.User;
import com.example.demo.util.ProductMapperHelper;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-24T21:13:51-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class FavoriteMapperImpl implements FavoriteMapper {

    @Autowired
    private ProductMapperHelper productMapperHelper;

    @Override
    public FavoriteDTO toDTO(Favorite favorite) {
        if ( favorite == null ) {
            return null;
        }

        FavoriteDTO favoriteDTO = new FavoriteDTO();

        favoriteDTO.setId( favorite.getId() );
        favoriteDTO.setUserId( favoriteUserId( favorite ) );
        favoriteDTO.setProductIds( productMapperHelper.mapProductsToIds( favorite.getProducts() ) );

        return favoriteDTO;
    }

    @Override
    public Favorite toEntity(FavoriteDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Favorite favorite = new Favorite();

        favorite.setUser( favoriteDTOToUser( dto ) );
        favorite.setId( dto.getId() );
        favorite.setProducts( productMapperHelper.mapIdsToProducts( dto.getProductIds() ) );

        return favorite;
    }

    private long favoriteUserId(Favorite favorite) {
        if ( favorite == null ) {
            return 0L;
        }
        User user = favorite.getUser();
        if ( user == null ) {
            return 0L;
        }
        long id = user.getId();
        return id;
    }

    protected User favoriteDTOToUser(FavoriteDTO favoriteDTO) {
        if ( favoriteDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( favoriteDTO.getUserId() );

        return user;
    }
}
