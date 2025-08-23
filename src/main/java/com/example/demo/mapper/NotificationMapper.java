package com.example.demo.mapper;

import com.example.demo.DTO.NotificationDTO;
import com.example.demo.entity.Notification;
import com.example.demo.util.DateFormatter;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import java.util.Date;

@Mapper(componentModel = "spring")
public interface NotificationMapper {

    @Mapping(source="user.id", target = "userId")
    NotificationDTO toDTO(Notification notification);

    @Mapping(source="userId", target = "user.id")
    Notification toEntity(NotificationDTO notificationDTO);

    @Named("convertStringToDate")
    default Date convertStringToDate(String dateString) {
        return DateFormatter.convertStringToDate(dateString);
    }

    @Named("convertDateToString")
    default String convertDateToString(Date date) {
        return DateFormatter.convertDateToString(date);
    }

}
