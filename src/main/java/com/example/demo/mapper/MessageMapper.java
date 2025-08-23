package com.example.demo.mapper;

import com.example.demo.DTO.MessageDTO;
import com.example.demo.entity.Message;
import com.example.demo.util.DateFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Date;

@Mapper(componentModel = "spring")
public interface MessageMapper {

    @Mapping(source= "room.id", target = "roomId")
    @Mapping(source = "date", target = "date", qualifiedByName = "convertDateToString")
    MessageDTO toDTO(Message message);

    @Mapping(source= "roomId", target = "room.id")
    @Mapping(source = "date", target = "date", qualifiedByName = "convertStringToDate")
    Message toEntity(MessageDTO messageDTO);

    @Named("convertStringToDate")
    default Date convertStringToDate(String dateString) {
        return DateFormatter.convertStringToDate(dateString);
    }

    @Named("convertDateToString")
    default String convertDateToString(Date date) {
        return DateFormatter.convertDateToString(date);
    }
}
