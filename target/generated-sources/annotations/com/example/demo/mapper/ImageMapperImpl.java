package com.example.demo.mapper;

import com.example.demo.DTO.ImageDTO;
import com.example.demo.entity.Image;
import com.example.demo.entity.Product;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-24T20:14:06-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class ImageMapperImpl implements ImageMapper {

    @Override
    public ImageDTO toDTO(Image image) {
        if ( image == null ) {
            return null;
        }

        ImageDTO imageDTO = new ImageDTO();

        imageDTO.setProductId( imageProductId( image ) );
        imageDTO.setId( image.getId() );
        imageDTO.setImage( image.getImage() );

        return imageDTO;
    }

    @Override
    public Image toEntity(ImageDTO imageDTO) {
        if ( imageDTO == null ) {
            return null;
        }

        Image image = new Image();

        image.setProduct( imageDTOToProduct( imageDTO ) );
        image.setId( imageDTO.getId() );
        image.setImage( imageDTO.getImage() );

        return image;
    }

    private long imageProductId(Image image) {
        if ( image == null ) {
            return 0L;
        }
        Product product = image.getProduct();
        if ( product == null ) {
            return 0L;
        }
        long id = product.getId();
        return id;
    }

    protected Product imageDTOToProduct(ImageDTO imageDTO) {
        if ( imageDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( imageDTO.getProductId() );

        return product;
    }
}
