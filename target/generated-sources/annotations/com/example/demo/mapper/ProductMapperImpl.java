package com.example.demo.mapper;

import com.example.demo.DTO.CategoryDTO;
import com.example.demo.DTO.ImageDTO;
import com.example.demo.DTO.ProductDTO;
import com.example.demo.entity.Category;
import com.example.demo.entity.Image;
import com.example.demo.entity.Product;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-24T21:13:51-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class ProductMapperImpl implements ProductMapper {

    @Autowired
    private ImageMapper imageMapper;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public ProductDTO toDto(Product product) {
        if ( product == null ) {
            return null;
        }

        ProductDTO productDTO = new ProductDTO();

        productDTO.setDate( convertDateToString( product.getDate() ) );
        productDTO.setId( product.getId() );
        productDTO.setName( product.getName() );
        productDTO.setDescription( product.getDescription() );
        productDTO.setPrice( product.getPrice() );
        productDTO.setLocation( product.getLocation() );
        productDTO.setStatus( product.getStatus() );
        productDTO.setCategories( categorySetToCategoryDTOSet( product.getCategories() ) );
        productDTO.setImages( imageSetToImageDTOList( product.getImages() ) );

        return productDTO;
    }

    @Override
    public Product toEntity(ProductDTO productDTO) {
        if ( productDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setDate( convertStringToDate( productDTO.getDate() ) );
        product.setId( productDTO.getId() );
        product.setName( productDTO.getName() );
        product.setDescription( productDTO.getDescription() );
        product.setPrice( productDTO.getPrice() );
        product.setLocation( productDTO.getLocation() );
        product.setStatus( productDTO.getStatus() );
        product.setCategories( categoryDTOSetToCategorySet( productDTO.getCategories() ) );
        product.setImages( imageDTOListToImageSet( productDTO.getImages() ) );

        return product;
    }

    protected Set<CategoryDTO> categorySetToCategoryDTOSet(Set<Category> set) {
        if ( set == null ) {
            return null;
        }

        Set<CategoryDTO> set1 = new LinkedHashSet<CategoryDTO>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( Category category : set ) {
            set1.add( categoryMapper.toDTO( category ) );
        }

        return set1;
    }

    protected List<ImageDTO> imageSetToImageDTOList(Set<Image> set) {
        if ( set == null ) {
            return null;
        }

        List<ImageDTO> list = new ArrayList<ImageDTO>( set.size() );
        for ( Image image : set ) {
            list.add( imageMapper.toDTO( image ) );
        }

        return list;
    }

    protected Set<Category> categoryDTOSetToCategorySet(Set<CategoryDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<Category> set1 = new LinkedHashSet<Category>( Math.max( (int) ( set.size() / .75f ) + 1, 16 ) );
        for ( CategoryDTO categoryDTO : set ) {
            set1.add( categoryMapper.toEntity( categoryDTO ) );
        }

        return set1;
    }

    protected Set<Image> imageDTOListToImageSet(List<ImageDTO> list) {
        if ( list == null ) {
            return null;
        }

        Set<Image> set = new LinkedHashSet<Image>( Math.max( (int) ( list.size() / .75f ) + 1, 16 ) );
        for ( ImageDTO imageDTO : list ) {
            set.add( imageMapper.toEntity( imageDTO ) );
        }

        return set;
    }
}
