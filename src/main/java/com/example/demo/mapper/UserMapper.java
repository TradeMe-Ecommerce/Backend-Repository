package com.example.demo.mapper;


import com.example.demo.DTO.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.util.DateFormatter;
import com.example.demo.util.InventoryMapperHelper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Date;

@Mapper(componentModel = "spring", uses = InventoryMapperHelper.class)
public interface UserMapper {

    @Mapping(source = "date", target = "date", qualifiedByName = "convertDateToString")
    @Mapping(source = "inventories", target = "inventoryIds")
    UserDTO toDTO(User user);

    @Mapping(source = "date", target = "date", qualifiedByName = "convertStringToDate")
    @Mapping(source = "inventoryIds", target = "inventories")
    User toEntity(UserDTO dto);

    @Named("convertStringToDate")
    default Date convertStringToDate(String dateString) {
        return DateFormatter.convertStringToDate(dateString);
    }

    @Named("convertDateToString")
    default String convertDateToString(Date date) {
        return DateFormatter.convertDateToString(date);
    }
}


