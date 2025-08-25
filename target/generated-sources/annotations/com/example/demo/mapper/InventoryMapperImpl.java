package com.example.demo.mapper;

import com.example.demo.DTO.InventoryDTO;
import com.example.demo.entity.Inventory;
import com.example.demo.entity.Product;
import com.example.demo.entity.User;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-25T07:28:33-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class InventoryMapperImpl implements InventoryMapper {

    @Override
    public Inventory toEntity(InventoryDTO inventory) {
        if ( inventory == null ) {
            return null;
        }

        Inventory inventory1 = new Inventory();

        inventory1.setUser( inventoryDTOToUser( inventory ) );
        inventory1.setProduct( inventoryDTOToProduct( inventory ) );
        inventory1.setId( inventory.getId() );
        inventory1.setStock( inventory.getStock() );

        return inventory1;
    }

    @Override
    public InventoryDTO toDTO(Inventory inventory) {
        if ( inventory == null ) {
            return null;
        }

        InventoryDTO inventoryDTO = new InventoryDTO();

        inventoryDTO.setUserId( inventoryUserId( inventory ) );
        inventoryDTO.setProductId( inventoryProductId( inventory ) );
        inventoryDTO.setId( inventory.getId() );
        if ( inventory.getStock() != null ) {
            inventoryDTO.setStock( inventory.getStock() );
        }

        return inventoryDTO;
    }

    protected User inventoryDTOToUser(InventoryDTO inventoryDTO) {
        if ( inventoryDTO == null ) {
            return null;
        }

        User user = new User();

        user.setId( inventoryDTO.getUserId() );

        return user;
    }

    protected Product inventoryDTOToProduct(InventoryDTO inventoryDTO) {
        if ( inventoryDTO == null ) {
            return null;
        }

        Product product = new Product();

        product.setId( inventoryDTO.getProductId() );

        return product;
    }

    private long inventoryUserId(Inventory inventory) {
        if ( inventory == null ) {
            return 0L;
        }
        User user = inventory.getUser();
        if ( user == null ) {
            return 0L;
        }
        long id = user.getId();
        return id;
    }

    private long inventoryProductId(Inventory inventory) {
        if ( inventory == null ) {
            return 0L;
        }
        Product product = inventory.getProduct();
        if ( product == null ) {
            return 0L;
        }
        long id = product.getId();
        return id;
    }
}
