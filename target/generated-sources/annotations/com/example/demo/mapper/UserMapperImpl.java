package com.example.demo.mapper;

import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.util.InventoryMapperHelper;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-08-24T20:14:07-0500",
    comments = "version: 1.5.5.Final, compiler: Eclipse JDT (IDE) 3.42.50.v20250729-0351, environment: Java 21.0.8 (Eclipse Adoptium)"
)
@Component
public class UserMapperImpl implements UserMapper {

    @Autowired
    private InventoryMapperHelper inventoryMapperHelper;

    @Override
    public UserDTO toDTO(User user) {
        if ( user == null ) {
            return null;
        }

        UserDTO userDTO = new UserDTO();

        userDTO.setDate( convertDateToString( user.getDate() ) );
        userDTO.setInventoryIds( inventoryMapperHelper.map( user.getInventories() ) );
        userDTO.setId( user.getId() );
        userDTO.setName( user.getName() );
        userDTO.setEmail( user.getEmail() );
        userDTO.setPhone( user.getPhone() );

        return userDTO;
    }

    @Override
    public User toEntity(UserDTO dto) {
        if ( dto == null ) {
            return null;
        }

        User user = new User();

        user.setDate( convertStringToDate( dto.getDate() ) );
        user.setInventories( inventoryMapperHelper.map( dto.getInventoryIds() ) );
        if ( dto.getId() != null ) {
            user.setId( dto.getId() );
        }
        user.setName( dto.getName() );
        user.setEmail( dto.getEmail() );
        user.setPhone( dto.getPhone() );

        return user;
    }
}
